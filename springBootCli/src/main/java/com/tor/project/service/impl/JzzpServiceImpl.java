package com.tor.project.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.base.Stopwatch;
import com.tor.project.enums.ZybrZylbEnum;
import com.tor.project.dto.FeatrueDTO;
import com.tor.project.dto.Fn35DTO;
import com.tor.project.entity.*;
import com.tor.project.mapper.primary.JzzpMapper;
import com.tor.project.mapper.primary.JzzptzzMapper;
import com.tor.project.mapper.primary.LdjgMapper;
import com.tor.project.mapper.primary.ZybrMapper;
import com.tor.project.mapper.second.JyJzzpOldMapper;
import com.tor.project.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tor.project.utils.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * 参保人员信息表-基准照片表 服务实现类
 * </p>
 *
 * @author Tzx
 * @since 2020-08-27
 */
@Slf4j
@Service
public class JzzpServiceImpl extends ServiceImpl<JzzpMapper, Jzzp> implements JzzpService {

    @Value("${migrate_jzpp_photo_path}")
    private String MIGRATE_JZPP_PHOTO_PATH;
    @Value("${handle_max_size}")
    private Integer HANDLE_MAX_SIZE;

    @Autowired
    private JzzpMapper jzzpMapper;
    @Autowired
    private LdjgMapper ldjgMapper;
    @Autowired
    private ZybrMapper zybrMapper;
    @Autowired
    private JzzptzzService jzzptzzService;
    @Autowired
    private JyJzzpOldMapper jyJzzpOldMapper;
    @Autowired
    private JzzpTemplateZptzzService jzzpTemplateZptzzService;
    @Autowired
    private TasktimeService tasktimeService;
    @Autowired
    private LdjgService ldjgService;
    @Autowired
    private ZybrService zybrService;


    private final static Lock INFO_LOCK = new ReentrantLock();
    private final static Lock PHOTO_LOCK = new ReentrantLock();
    private final static Lock LDJG_LOCK = new ReentrantLock();
    private final static Lock ZYBR_LOCK = new ReentrantLock();
    private final static Lock ZYBRCY_LOCK = new ReentrantLock();

    /**
     * 金华迁移照片
     */
    @Override
    public void migrateJzppJhPhotosJob() {
        FormatedLogUtil logUtil = new FormatedLogUtil();
        Stopwatch started = null;
        PHOTO_LOCK.lock();
        try {

            started = Stopwatch.createStarted();
            logUtil.append(StrUtil.format("threadNmae:{} 获取到锁", Thread.currentThread().getName()));
            QueryWrapper<Jzzp> wrapper = new QueryWrapper<Jzzp>()
                    .select("THREAD_NUMBER")
                    .isNotNull("THREAD_NUMBER")
                    .isNull("FZZD")
                    .groupBy("THREAD_NUMBER");
            List<Jzzp> list = list(wrapper);
            log.info(new FormatedLogUtil(StrUtil.format("thread_number size:{}", list.size())).getLogString());
            CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(list.size());
            list.forEach(jzzp -> ThreadUtil.execute(() -> {
                migrateJzppPhotos(jzzp.getThreadNumber());
                countDownLatch.countDown();
            }));
            countDownLatch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            PHOTO_LOCK.unlock();
            logUtil.append(StrUtil.format("释放锁,cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
            if (logUtil.isSucc()) {
                log.info(logUtil.getLogString());
            } else {
                log.error(logUtil.getLogString());
            }
        }
    }

    private void migrateJzppPhotos(int num) {
        QueryWrapper<Jzzp> wrapper = new QueryWrapper<Jzzp>()
                .select("ZPID,ZPLJ,LSZPLJ")
                .eq("THREAD_NUMBER", num)
                .isNull("FZZD");
        List<Jzzp> list = list(wrapper);
        for (Jzzp jzzp : list) {
            FormatedLogUtil logUtil = new FormatedLogUtil();
            Stopwatch started = Stopwatch.createStarted();
            try {
                int status = -1;
                // 第一模板照
                if (StringUtils.isNotBlank(jzzp.getZplj())) {
                    logUtil.append(StrUtil.format("zplj:{}{}", MIGRATE_JZPP_PHOTO_PATH, jzzp.getZplj()));
                    byte[] bytes = getPhotosByte(jzzp.getZplj());
                    if (null == bytes || bytes.length < 1) {
                        logUtil.append("photos byte is null");
                    } else {
                        FeatrueDTO featrueDTO = ServiceUtils.getInstance().extractImageFeature(bytes);
                        if (!featrueDTO.isSucc()) {
                            logUtil.append("无法提取特征值");
                        } else {
                            Jzzptzz jzzptzz = new Jzzptzz();
                            jzzptzz.setZpid(jzzp.getZpid());
                            jzzptzz.setZptzz(featrueDTO.getFeatureStr());
                            jzzptzz.setBbh(featrueDTO.getBbh());
                            String newZpljPaht = FileStorager.upload(bytes);
                            if (StringUtils.isBlank(newZpljPaht)) {
                                logUtil.append("upload failed");
                            } else {
                                status = 1;
                                jzzp.setZplj(newZpljPaht);
                            }
                            jzzptzzService.saveOrUpdate(jzzptzz);
                        }
                    }
                }
                // 第二模板照
                if (StringUtils.isNotBlank(jzzp.getLszplj())) {
                    logUtil.append(StrUtil.format("lszplj:{}{}", MIGRATE_JZPP_PHOTO_PATH, jzzp.getLszplj()));
                    byte[] bytes = getPhotosByte(jzzp.getLszplj());
                    if (null == bytes || bytes.length < 1) {
                        logUtil.append("photos byte is null");
                    } else {
                        FeatrueDTO featrueDTO = ServiceUtils.getInstance().extractImageFeature(bytes);
                        if (!featrueDTO.isSucc()) {
                            logUtil.append("无法提取特征值");
                        } else {
                            String newZpljPaht = FileStorager.upload(bytes);
                            logUtil.append(StrUtil.format("newZpljPaht={}", newZpljPaht));
                            if (StringUtils.isNotBlank(newZpljPaht)) {
                                JzzpTemplateZptzz jzzpTemplateZptzz = new JzzpTemplateZptzz();
                                jzzpTemplateZptzz.setZpid(jzzp.getZpid());
                                jzzpTemplateZptzz.setZptzz(featrueDTO.getFeatureStr());
                                jzzpTemplateZptzz.setBbh(featrueDTO.getBbh());
                                jzzp.setLszplj(newZpljPaht);
                                jzzpTemplateZptzzService.saveOrUpdate(jzzpTemplateZptzz);
                                status = 1;
                            } else {
                                logUtil.append("upload failed");
                            }
                        }
                    }
                }
                jzzp.setFzzd(status);
                this.updateById(jzzp);
                logUtil.append(StrUtil.format("update:{}", status == -1 ? "failed" : status == 1 ? "success" : ""));
            } catch (Exception e) {
                logUtil.setSucc(false).append(LogUtils.getTrace(e));
            } finally {
                logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
                if (logUtil.isSucc()) {
                    log.info(logUtil.getLogString());
                } else {
                    log.error(logUtil.getLogString());
                }
            }
        }
    }

    private byte[] getPhotosByte(String path) {
        if (StringUtils.isBlank(path)) {
            return null;
        }
        String photoPath = MIGRATE_JZPP_PHOTO_PATH + path;
        File file = new File(photoPath);
        return fileToByte(file);
    }

    public static byte[] fileToByte(File img) {
        if (!img.exists()) {
            return null;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedImage bi = ImageIO.read(img);
            ImageIO.write(bi, "jpg", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public void migrateJzppHyInfoJob() {
        if (!RegVerUtils.isHy()) {
            log.info("非海盐版本");
            return;
        }
        INFO_LOCK.lock();
        FormatedLogUtil logUtil = new FormatedLogUtil("同步海盐参保人员信息及照片==>");
        Stopwatch started = Stopwatch.createStarted();
        try {
            log.info("开始同步海盐参保人员照片信息");
            baseMapper.migrateJzppHyPhotoJob();
            log.info("开始同步海盐参保人员信息");
            baseMapper.migrateJzppHyInfoJob();
            log.info("参保人员信息同步完成cost.ms={}", started.elapsed(TimeUnit.MILLISECONDS));
            logUtil.append("同步完成");
        } catch (Exception e) {
            logUtil.setSucc(false).append(LogUtils.getTrace(e));
        } finally {
            INFO_LOCK.unlock();
            logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
            if (logUtil.isSucc()) {
                log.info(logUtil.getLogString());
            } else {
                log.error(logUtil.getLogString());
            }
        }
    }

    /**
     * 海盐迁移照片
     */
    @Override
    public void migrateJzppHyPhotosJob() {
        if (!RegVerUtils.isHy()) {
            log.info("非海盐版本");
            return;
        }
        FormatedLogUtil logUtil = new FormatedLogUtil();
        Stopwatch started = null;
        PHOTO_LOCK.lock();
        try {

            started = Stopwatch.createStarted();
            logUtil.append(StrUtil.format("threadNmae:{} 获取到锁", Thread.currentThread().getName()));
            QueryWrapper<Jzzp> wrapper = new QueryWrapper<Jzzp>()
                    .select("THREAD_NUMBER")
                    /*.isNotNull("THREAD_NUMBER")
                    .isNull("FZZD")*/
                    .groupBy("THREAD_NUMBER");
            List<Jzzp> list = list(wrapper);
            log.info(new FormatedLogUtil(StrUtil.format("thread_number size:{}", list.size())).getLogString());
            CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(list.size());
            list.forEach(jzzp -> ThreadUtil.execute(() -> {
                migrateJzppHyPhotos(jzzp.getThreadNumber());
                countDownLatch.countDown();
            }));
            countDownLatch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            PHOTO_LOCK.unlock();
            logUtil.append(StrUtil.format("释放锁,cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
            if (logUtil.isSucc()) {
                log.info(logUtil.getLogString());
            } else {
                log.error(logUtil.getLogString());
            }
        }
    }

    private void migrateJzppHyPhotos(int num) {
        QueryWrapper<Jzzp> wrapper = new QueryWrapper<Jzzp>()
                .select("ZPID,SFZH,XM")
                .eq("THREAD_NUMBER", num)
                .isNull("ZPLJ")
                /*.isNull("FZZD")*/;
        List<Jzzp> list = list(wrapper);
        log.info(new FormatedLogUtil(StrUtil.format("thread_number:{} list_size:{}", num, list.size())).getLogString());
        for (Jzzp jzzp : list) {
            FormatedLogUtil logUtil = new FormatedLogUtil();
            logUtil.append(StrUtil.format("sfzh={} xm={}", jzzp.getSfzh(), jzzp.getXm()));
            Stopwatch started = Stopwatch.createStarted();
            try {
                if (StringUtils.isBlank(jzzp.getSfzh()) && StringUtils.isBlank(jzzp.getXm())) {
                    continue;
                }
                List<HyJzzpPhoto> jzzpPhotos = baseMapper.getHyJzzpZpBlobBySfzhAndXm(jzzp.getSfzh(), jzzp.getXm());
                if (CollectionUtil.isEmpty(jzzpPhotos)) {
                    continue;
                }
                HyJzzpPhoto jzzpPhoto = jzzpPhotos.get(0);
                if (null == jzzpPhoto.getZp() || jzzpPhoto.getZp().length < 1) {
                    continue;
                }
                logUtil.append(StrUtil.format("found jzzpPhoto"));
                FeatrueDTO featrueDTO = ServiceUtils.getInstance().extractImageFeature(jzzpPhoto.getZp());
                logUtil.append(StrUtil.format("featrueDTO.isSucc:{}", featrueDTO.isSucc()));
                if (!featrueDTO.isSucc()) {
                    continue;
                }
                Jzzptzz jzzptzz = new Jzzptzz();
                jzzptzz.setZpid(jzzp.getZpid());
                jzzptzz.setZptzz(featrueDTO.getFeatureStr());
                jzzptzz.setBbh(featrueDTO.getBbh());
                String jzzpPhotoPath = FileStorager.upload(jzzpPhoto.getZp());
                logUtil.append(StrUtil.format("jzzpPhotoPathIsExist:{}", StringUtils.isNotBlank(jzzpPhotoPath)));
                if (StringUtils.isBlank(jzzpPhotoPath)) {
                    continue;
                }
                jzzp.setFzzd(1);
                jzzp.setZplj(jzzpPhotoPath);
                this.updateById(jzzp);
                jzzptzzService.saveOrUpdate(jzzptzz);
                logUtil.append(StrUtil.format("upload photo success"));
            } catch (Exception e) {
                logUtil.setSucc(false).append(LogUtils.getTrace(e));
            } finally {
                logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
                if (logUtil.isSucc()) {
                    log.info(logUtil.getLogString());
                } else {
                    log.error(logUtil.getLogString());
                }
            }
        }
    }


    /**
     * 江阴照片迁移
     */
    @Override
    public void migrateJzppJyPhotosJob() {
        if (!RegVerUtils.isJy()) {
            log.info("非江阴版本");
            return;
        }
        log.info(new FormatedLogUtil().append("=============start migrateJzppJyPhotosJob==============").getLogString());
        /*if (!PHOTO_LOCK.tryLock()) {
            log.info(new FormatedLogUtil().append("migrateJzppJyPhotosJob Lock not acquired;exit the current thread").getLogString());
            return;
        }*/
        /*PHOTO_LOCK.lock();*/
        FormatedLogUtil logUtil = new FormatedLogUtil();
        Stopwatch started = Stopwatch.createStarted();
        try {
            started = Stopwatch.createStarted();
            try {
                baseMapper.migrateJyLdjgInfo();
                logUtil.append("migrateJyLdjgInfo success");
                log.info(logUtil.getLogString());
            } catch (Exception e) {
                e.printStackTrace();
                logUtil.append("migrateJyLdjgInfo failed");
                logUtil.setSucc(false).append(LogUtils.getTrace(e));
            }
            log.info(logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS))).getLogString());
            try {
                baseMapper.migrateJyJzppInfo();
                logUtil.append("migrateJyJzppInfo success");
            } catch (Exception e) {
                e.printStackTrace();
                logUtil.append("migrateJyJzppInfo failed");
                logUtil.setSucc(false).append(LogUtils.getTrace(e));
            }
            log.info(logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS))).getLogString());
            logUtil.append(StrUtil.format("threadNmae:{} 获取到锁", Thread.currentThread().getName()));
            QueryWrapper<Jzzp> wrapper = new QueryWrapper<Jzzp>()
                    .select("THREAD_NUMBER")
                    .isNotNull("THREAD_NUMBER")
                    .isNull("FZZD")
                    .groupBy("THREAD_NUMBER");
            List<Jzzp> list = list(wrapper);
            log.info(new FormatedLogUtil(StrUtil.format("thread_number size:{}", list.size())).getLogString());
            CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(list.size());
            list.forEach(jzzp -> ThreadUtil.execute(() -> {
                migrateJzppJyPhotos(jzzp.getThreadNumber());
                countDownLatch.countDown();
            }));
            countDownLatch.await();
        } catch (InterruptedException e) {
            logUtil.setSucc(false).append(LogUtils.getTrace(e));
        } finally {
            /*PHOTO_LOCK.unlock();*/
            logUtil.append(StrUtil.format("释放锁,cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
            if (logUtil.isSucc()) {
                log.info(logUtil.getLogString());
            } else {
                log.error(logUtil.getLogString());
            }
        }
    }

    private void migrateJzppJyPhotos(int num) {
        QueryWrapper<Jzzp> wrapper = new QueryWrapper<Jzzp>()
                .select("ZPID,SFZH,XM")
                .eq("THREAD_NUMBER", num)
                .isNull("ZPLJ")
                .isNull("FZZD");
        List<Jzzp> list = list(wrapper);
        log.info(new FormatedLogUtil(StrUtil.format("thread_number:{} list_size:{}", num, list.size())).getLogString());
        for (Jzzp jzzp : list) {
            FormatedLogUtil logUtil = new FormatedLogUtil();
            logUtil.append(StrUtil.format("sfzh={} xm={}", jzzp.getSfzh(), jzzp.getXm()));
            Stopwatch started = Stopwatch.createStarted();
            try {
                if (StringUtils.isBlank(jzzp.getSfzh()) && StringUtils.isBlank(jzzp.getXm())) {
                    continue;
                }
                List<JyJzzpPhoto> jzzpPhotos = jyJzzpOldMapper.getJyJzzpZpBlobBySfzhAndXm(jzzp.getSfzh(), jzzp.getXm());
                if (CollectionUtil.isEmpty(jzzpPhotos)) {
                    logUtil.append("jzzpPhotos is empty");
                    continue;
                }
                JyJzzpPhoto jzzpPhoto = jzzpPhotos.get(0);
                if (null == jzzpPhoto.getPhoto() || jzzpPhoto.getPhoto().length < 1) {
                    continue;
                }
                logUtil.append(StrUtil.format("found jzzpPhoto"));
                FeatrueDTO featrueDTO = ServiceUtils.getInstance().extractImageFeature(jzzpPhoto.getPhoto());
                logUtil.append(StrUtil.format("featrueDTO.isSucc:{}", featrueDTO.isSucc()));
                if (!featrueDTO.isSucc()) {
                    continue;
                }
                Jzzptzz jzzptzz = new Jzzptzz();
                jzzptzz.setZpid(jzzp.getZpid());
                jzzptzz.setZptzz(featrueDTO.getFeatureStr());
                jzzptzz.setBbh(featrueDTO.getBbh());
                String jzzpPhotoPath = FileStorager.upload(jzzpPhoto.getPhoto());
                logUtil.append(StrUtil.format("jzzpPhotoPathIsExist:{}", StringUtils.isNotBlank(jzzpPhotoPath)));
                if (StringUtils.isBlank(jzzpPhotoPath)) {
                    continue;
                }
                jzzp.setFzzd(1);
                jzzp.setZplj(jzzpPhotoPath);
                this.updateById(jzzp);
                jzzptzzService.saveOrUpdate(jzzptzz);
                logUtil.append(StrUtil.format("upload photo success"));
            } catch (Exception e) {
                logUtil.setSucc(false).append(LogUtils.getTrace(e));
            } finally {
                logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
                if (logUtil.isSucc()) {
                    log.info(logUtil.getLogString());
                } else {
                    log.error(logUtil.getLogString());
                }
            }
        }
    }


    @Override
    public void migrateJyJzppInfo() {
        if (!RegVerUtils.isJy()) {
            log.info("非江阴版本");
            return;
        }
        log.info(new FormatedLogUtil().append("=============start migrateJyJzppInfo==============").getLogString());
        /*if (!INFO_LOCK.tryLock()) {
            log.info(new FormatedLogUtil().append("migrateJyJzppInfo Lock not acquired;exit the current thread").getLogString());
            return;
        }*/
        /*INFO_LOCK.lock();*/
        FormatedLogUtil logUtil = new FormatedLogUtil("migrateJyJzppInfo==>获取到INFO_LOCK锁");
        Stopwatch started = Stopwatch.createStarted();
        log.info(logUtil.getLogString());
        try {
            started = Stopwatch.createStarted();
            try {
                baseMapper.migrateJyZybrkSInfo();
                baseMapper.migrateJyZybrChInfo();
                baseMapper.migrateJyZybrInfo();
                logUtil.append("migrateJyZybrkSInfo success");
            } catch (Exception e) {
                e.printStackTrace();
                logUtil.append("migrateJyZybrkSInfo failed");
                logUtil.setSucc(false).append(LogUtils.getTrace(e));
            }
            log.info(logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS))).getLogString());
            try {
                baseMapper.migrateJyZybrCyInfo();
                logUtil.append("migrateJyZybrCyInfo success");
            } catch (Exception e) {
                e.printStackTrace();
                logUtil.append("migrateJyZybrCyInfo failed");
                logUtil.setSucc(false).append(LogUtils.getTrace(e));
            }
            log.info(logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS))).getLogString());
        } catch (Exception e) {
            logUtil.setSucc(false).append(LogUtils.getTrace(e));
        } finally {
            /*INFO_LOCK.unlock();*/
            logUtil.append("migrateJyJzppInfo==>释放INFO_LOCK锁");
            log.info(logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS))).getLogString());
            logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
            if (logUtil.isSucc()) {
                log.info(logUtil.getLogString());
            } else {
                log.error(logUtil.getLogString());
            }
        }
    }

    @Override
    public void migrateQhJzppInfo() {
        if (!RegVerUtils.isQingHai()) {
            log.info("非青海版本");
            return;
        }
        if (!INFO_LOCK.tryLock()) {
            log.info(new FormatedLogUtil().append("migrateQhJzppInfo Lock not acquired;exit the current thread").getLogString());
            return;
        }
        INFO_LOCK.lock();
        log.info(new FormatedLogUtil().append("=============start migrateQhJzppInfo==============").getLogString());
        FormatedLogUtil startLogUtil = new FormatedLogUtil();
        Stopwatch startStarted = Stopwatch.createStarted();
        try {
            while (true) {
                Tasktime tasktime = tasktimeService.list(new LambdaQueryWrapper<Tasktime>().eq(Tasktime::getBj, 1)).get(0);
                String tasktimeString = DateUtil.format(tasktime.getTasktime(), DatePattern.NORM_DATETIME_FORMAT);
                log.info(StrUtil.format("=============start tasktimeString:{}============", tasktimeString));
                List<QhJzzpInfo> jzzpInfoList = jzzpMapper.getQhJzzpInfoByGxsj(tasktimeString, HANDLE_MAX_SIZE);
                log.info(new FormatedLogUtil(StrUtil.format("jzzpInfoList.size={}", jzzpInfoList.size())).getLogString());
                if (jzzpInfoList.size() < 1) {
                    break;
                }
                for (QhJzzpInfo jzzpInfo : jzzpInfoList) {
                    FormatedLogUtil logUtil = new FormatedLogUtil(StrUtil.format("grbh={},sfzh={},xm={},tcq={}", jzzpInfo.getGrbh(), jzzpInfo.getSfzh(), jzzpInfo.getXm(), jzzpInfo.getTcq()));
                    Stopwatch started = Stopwatch.createStarted();
                    Jzzp jzzp = getOne(new LambdaQueryWrapper<Jzzp>().eq(Jzzp::getPersonalnumber, jzzpInfo.getGrbh()));
                    try {
                        if (ObjectUtil.isNull(jzzp)) {
                            jzzp = new Jzzp();
                        }
                        jzzp.setTjsj(new Date());
                        jzzp.setPersonalnumber(jzzpInfo.getGrbh());
                        jzzp.setSfzh(jzzpInfo.getSfzh());
                        jzzp.setXm(jzzpInfo.getXm());
                        jzzp.setSbkh(jzzpInfo.getSbkh());
                        // 移除统筹区末尾的0
                        jzzp.setInsuredAreaCode(remove_ending_with_0(jzzpInfo.getTcq()));
                        if (StringUtils.isBlank(jzzp.getZpid())) {
                            jzzp.setThreadNumber((NumberUtil.generateRandomNumber(0, 5, 1))[0]);
                            jzzpMapper.saveJzzp(jzzp);
                            logUtil.append("insert success");
                        } else {
                            jzzpMapper.updateById(jzzp);
                            logUtil.append("update success");
                        }
                    } catch (Exception e) {
                        logUtil.setSucc(false).append(LogUtils.getTrace(e));
                    } finally {
                        logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
                        if (logUtil.isSucc()) {
                            log.info(logUtil.getLogString());
                        } else {
                            log.error(logUtil.getLogString());
                        }
                    }
                    tasktime.setTasktime(jzzpInfo.getGxsj());
                }
                tasktimeService.updateById(tasktime);
                log.info(StrUtil.format("=============end tasktimeString:{}============", tasktime.getTasktime()));
            }
        } catch (Exception e) {
            startLogUtil.setSucc(false).append(LogUtils.getTrace(e));
        } finally {
            INFO_LOCK.unlock();
            startLogUtil.append("migrateQhJzppInfo==>释放INFO_LOCK锁");
            startLogUtil.append(StrUtil.format("cost.time={}", startStarted.elapsed(TimeUnit.MILLISECONDS)));
            if (startLogUtil.isSucc()) {
                log.info(startLogUtil.getLogString());
            } else {
                log.error(startLogUtil.getLogString());
            }
        }
    }

    @Override
    public void migrateQhLdjgInfo() {
        if (!RegVerUtils.isQingHai()) {
            log.info("非青海版本");
            return;
        }
        if (!LDJG_LOCK.tryLock()) {
            log.info(new FormatedLogUtil().append("migrateQhLdjgInfo Lock not acquired;exit the current thread").getLogString());
            return;
        }
        LDJG_LOCK.lock();
        log.info(new FormatedLogUtil().append("=============start migrateQhLdjgInfo==============").getLogString());
        FormatedLogUtil startLogUtil = new FormatedLogUtil();
        Stopwatch startStarted = Stopwatch.createStarted();
        try {
            while (true) {
                Tasktime tasktime = tasktimeService.list(new LambdaQueryWrapper<Tasktime>().eq(Tasktime::getBj, 2)).get(0);
                String tasktimeString = DateUtil.format(tasktime.getTasktime(), DatePattern.NORM_DATETIME_FORMAT);
                log.info(StrUtil.format("=============start tasktimeString:{}============", tasktimeString));
                List<Ldjg> LdjgList = jzzpMapper.getQhLdjgInfoByGxsj(tasktimeString, 10000);
                log.info(new FormatedLogUtil(StrUtil.format("LdjgList.size={}", LdjgList.size())).getLogString());
                if (LdjgList.size() < 1) {
                    break;
                }
                for (Ldjg ldjgNow : LdjgList) {
                    FormatedLogUtil logUtil = new FormatedLogUtil(StrUtil.format("jgdm={},jgmc={}", ldjgNow.getJgdm(), ldjgNow.getJgmc()));
                    Stopwatch started = Stopwatch.createStarted();
                    try {
                        Ldjg ldjg = ldjgService.getOne(new LambdaQueryWrapper<Ldjg>().eq(Ldjg::getJgdm, ldjgNow.getJgdm()));
                        if (ObjectUtil.isNull(ldjg)) {
                            ldjg = new Ldjg();
                        }
                        ldjg.setInpatient(1);
                        ldjg.setAddtime(new Date());
                        ldjg.setJgdm(ldjgNow.getJgdm());
                        ldjg.setJgmc(ldjgNow.getJgmc());
                        ldjg.setLxdz(ldjgNow.getLxdz());
                        ldjg.setLxr(ldjgNow.getLxr());
                        ldjg.setLxdh(ldjgNow.getLxdh());
                        ldjg.setJglx(ldjgNow.getJglx());
                        ldjg.setJgdm(ldjgNow.getJgdm());
                        ldjg.setAddtime(ldjgNow.getAddtime());
                        ldjg.setInstitutionNo(ldjgNow.getJgdm());
                        ldjg.setQxdm(remove_ending_with_0(ldjgNow.getQxdm()));
                        ldjg.setJglbdm(ObjectUtil.equal(ldjgNow.getJglx(), 1) ? "A1" : "E1");
                        if (StringUtils.isBlank(ldjg.getId())) {
                            ldjgMapper.saveLdjg(ldjg);
                            logUtil.append("insert success");
                        } else {
                            ldjgMapper.updateById(ldjg);
                            logUtil.append("update success");
                        }
                    } catch (Exception e) {
                        logUtil.setSucc(false).append(LogUtils.getTrace(e));
                    } finally {
                        logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
                        if (logUtil.isSucc()) {
                            log.info(logUtil.getLogString());
                        } else {
                            log.error(logUtil.getLogString());
                        }
                    }
                    tasktime.setTasktime(ldjgNow.getAddtime());
                }
                tasktimeService.updateById(tasktime);
                log.info(StrUtil.format("=============end tasktimeString:{}============", tasktime.getTasktime()));
            }
        } catch (Exception e) {
            startLogUtil.setSucc(false).append(LogUtils.getTrace(e));
        } finally {
            LDJG_LOCK.unlock();
            startLogUtil.append("migrateQhLdjgInfo==>释放LDJG_LOCK锁");
            startLogUtil.append(StrUtil.format("cost.time={}", startStarted.elapsed(TimeUnit.MILLISECONDS)));
            if (startLogUtil.isSucc()) {
                log.info(startLogUtil.getLogString());
            } else {
                log.error(startLogUtil.getLogString());
            }
        }
    }

    @Override
    public void migrateQhZybrInfo() {
        if (!RegVerUtils.isQingHai()) {
            log.info("非青海版本");
            return;
        }
        if (!ZYBR_LOCK.tryLock()) {
            log.info(new FormatedLogUtil().append("migrateQhZybrInfo Lock not acquired;exit the current thread").getLogString());
            return;
        }
        ZYBR_LOCK.lock();
        log.info(new FormatedLogUtil().append("=============start migrateQhZybrInfo==============").getLogString());
        FormatedLogUtil startLogUtil = new FormatedLogUtil();
        Stopwatch startStarted = Stopwatch.createStarted();
        try {
            List<QhZybrInfo> zybrInfoList = jzzpMapper.getQhZybrInfo();
            log.info(new FormatedLogUtil().append(StrUtil.format("=============jzzpMapper.getQhZybrInfo().size={}==============", zybrInfoList.size())).getLogString());
            for (QhZybrInfo info : zybrInfoList) {
                FormatedLogUtil logUtil = new FormatedLogUtil(StrUtil.format("jgdm={},grbh={},rysj={}", info.getJgdm(), info.getGrbh(), info.getRysj()));
                Stopwatch started = Stopwatch.createStarted();
                try {
                    Ldjg ldjg = ldjgService.getOne(new LambdaQueryWrapper<Ldjg>().eq(Ldjg::getJgdm, info.getJgdm()));
                    if (ObjectUtil.isNull(ldjg)) {
                        logUtil.append("未找到对应的两定机构");
                        continue;
                    }
                    Jzzp jzzp = getOne(new LambdaQueryWrapper<Jzzp>().eq(Jzzp::getPersonalnumber, info.getGrbh()));
                    if (ObjectUtil.isNull(jzzp)) {
                        logUtil.append("未找到对应的参保人");
                        continue;
                    }
                    Zybr zybr = zybrService.getOne(new LambdaQueryWrapper<Zybr>()
                            .eq(Zybr::getZpid, jzzp.getZpid())
                            .eq(Zybr::getYldwid, ldjg.getId())
                            .eq(Zybr::getRzsj, info.getRysj()));
                    if (ObjectUtil.isNotNull(zybr)) {
                        logUtil.append("该病人已入院");
                        continue;
                    }
                    zybr = new Zybr();
                    zybr.setCh(info.getCh());
                    zybr.setSfzh(info.getSfzh());
                    zybr.setXm(info.getXm());
                    zybr.setRzsj(info.getRysj());
                    zybr.setYldwid(ldjg.getId());
                    zybr.setZpid(jzzp.getZpid());
                    zybr.setHospitalcategory(ZybrZylbEnum.getCodeByName(info.getZylb()));
                    zybr.setDiseaseDiagnosis(info.getJbzd());
                    zybr.setNaturePersonnel(info.getRysz());
                    zybr.setInsuredUnit(info.getCbdw());
                    // 移除统筹区末尾的0
                    zybr.setInsuredAreaCode(remove_ending_with_0(info.getTcq()));
                    zybrMapper.saveZybr(zybr);
                    logUtil.append("insert success");
                } catch (Exception e) {
                    logUtil.setSucc(false).append(LogUtils.getTrace(e));
                } finally {
                    logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
                    if (logUtil.isSucc()) {
                        log.info(logUtil.getLogString());
                    } else {
                        log.error(logUtil.getLogString());
                    }
                }
            }
        } catch (Exception e) {
            startLogUtil.setSucc(false).append(LogUtils.getTrace(e));
        } finally {
            ZYBR_LOCK.unlock();
            startLogUtil.append("migrateQhZybrInfo==>释放ZYBR_LOCK锁");
            startLogUtil.append(StrUtil.format("cost.time={}", startStarted.elapsed(TimeUnit.MILLISECONDS)));
            if (startLogUtil.isSucc()) {
                log.info(startLogUtil.getLogString());
            } else {
                log.error(startLogUtil.getLogString());
            }
        }
    }

    /**
     * 删除末尾所有0
     *
     * @param s
     * @return
     */
    private static String remove_ending_with_0(String s) {
        if (StringUtils.isNotBlank(s))
            while (s.substring(s.length() - 1).equals("0")) s = s.substring(0, s.length() - 1);
        return s;
    }

    @Override
    public void migrateQhZybrCyInfo() {
        if (!RegVerUtils.isQingHai()) {
            log.info("非青海版本");
            return;
        }
        if (!ZYBRCY_LOCK.tryLock()) {
            log.info(new FormatedLogUtil().append("migrateQhZybrCyInfo Lock not acquired;exit the current thread").getLogString());
            return;
        }
        ZYBRCY_LOCK.lock();
        log.info(new FormatedLogUtil().append("=============start migrateQhZybrCyInfo==============").getLogString());
        FormatedLogUtil startLogUtil = new FormatedLogUtil();
        Stopwatch startStarted = Stopwatch.createStarted();
        try {
            List<Zybr> zybrList = zybrService.list(new LambdaQueryWrapper<Zybr>().eq(Zybr::getSfcy, 0));
            for (Zybr zybr : zybrList) {
                FormatedLogUtil logUtil = new FormatedLogUtil(StrUtil.format("yldwid={},zpid={}", zybr.getYldwid(), zybr.getZpid()));
                Stopwatch started = Stopwatch.createStarted();
                try {
                    Ldjg ldjg = ldjgService.getById(zybr.getYldwid());
                    if (ObjectUtil.isNull(ldjg)) {
                        logUtil.append("未找到对应的两定机构");
                        continue;
                    }
                    Jzzp jzzp = getById(zybr.getZpid());
                    if (ObjectUtil.isNull(jzzp)) {
                        logUtil.append("未找到对应的参保人");
                        continue;
                    }
                    logUtil.append(StrUtil.format("jgdm={},grbh={}", ldjg.getJgdm(), jzzp.getPersonalnumber()));
                    List<QhZybrInfo> zybrCyInfo = jzzpMapper.getQhZybrCyInfo(ldjg.getJgdm(), jzzp.getPersonalnumber(), zybr.getRzsj());
                    if (CollectionUtil.isEmpty(zybrCyInfo)) {
                        logUtil.append("患者还未出院");
                        continue;
                    }
                    QhZybrInfo zybrInfo = zybrCyInfo.get(0);
                    zybr.setSfcy(1);
                    zybr.setCysj(zybrInfo.getCysj());
                    zybrMapper.updateById(zybr);
                    logUtil.append("出院成功");
                } catch (Exception e) {
                    logUtil.setSucc(false).append(LogUtils.getTrace(e));
                } finally {
                    logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
                    if (logUtil.isSucc()) {
                        log.info(logUtil.getLogString());
                    } else {
                        log.error(logUtil.getLogString());
                    }
                }
            }
        } catch (Exception e) {
            startLogUtil.setSucc(false).append(LogUtils.getTrace(e));
        } finally {
            ZYBRCY_LOCK.unlock();
            startLogUtil.append("migrateQhZybrCyInfo==>释放ZYBR_LOCK锁");
            startLogUtil.append(StrUtil.format("cost.time={}", startStarted.elapsed(TimeUnit.MILLISECONDS)));
            if (startLogUtil.isSucc()) {
                log.info(startLogUtil.getLogString());
            } else {
                log.error(startLogUtil.getLogString());
            }
        }
    }

    @Override
    public void migrateQhJzppPhotos() {
        if (!RegVerUtils.isQingHai()) {
            log.info("非青海版本");
            return;
        }
        log.info(new FormatedLogUtil().append("=============start migrateQhJzppPhotos==============").getLogString());
        if (!PHOTO_LOCK.tryLock()) {
            log.info(new FormatedLogUtil().append("migrateQhJzppPhotos Lock not acquired;exit the current thread").getLogString());
            return;
        }
        PHOTO_LOCK.lock();
        FormatedLogUtil logUtil = new FormatedLogUtil();
        Stopwatch started = Stopwatch.createStarted();
        try {
            logUtil.append(StrUtil.format("threadNmae:{} 获取到锁", Thread.currentThread().getName()));
            QueryWrapper<Jzzp> wrapper = new QueryWrapper<Jzzp>()
                    .select("THREAD_NUMBER")
                    .isNotNull("THREAD_NUMBER")
                    .isNull("FZZD")
                    .groupBy("THREAD_NUMBER");
            List<Jzzp> list = list(wrapper);
            log.info(new FormatedLogUtil(StrUtil.format("thread_number size:{}", list.size())).getLogString());
            CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(list.size());
            list.forEach(jzzp -> ThreadUtil.execute(() -> {
                migrateJzppQhPhotos(jzzp.getThreadNumber());
                countDownLatch.countDown();
            }));
            countDownLatch.await();
        } catch (InterruptedException e) {
            logUtil.setSucc(false).append(LogUtils.getTrace(e));
        } finally {
            PHOTO_LOCK.unlock();
            logUtil.append(StrUtil.format("释放锁,cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
            if (logUtil.isSucc()) {
                log.info(logUtil.getLogString());
            } else {
                log.error(logUtil.getLogString());
            }
        }
    }

    /**
     * 青海
     *
     * @param num
     */
    private void migrateJzppQhPhotos(int num) {
        QueryWrapper<Jzzp> wrapper = new QueryWrapper<Jzzp>()
                .select("ZPID,SFZH,XM")
                .eq("THREAD_NUMBER", num)
                .isNull("ZPLJ")
                .isNull("FZZD");
        List<Jzzp> list = list(wrapper);
        log.info(new FormatedLogUtil(StrUtil.format("thread_number:{} list_size:{}", num, list.size())).getLogString());
        for (Jzzp jzzp : list) {
            FormatedLogUtil logUtil = new FormatedLogUtil();
            logUtil.append(StrUtil.format("sfzh={} xm={}", jzzp.getSfzh(), jzzp.getXm()));
            Stopwatch started = Stopwatch.createStarted();
            try {
                if (StringUtils.isBlank(jzzp.getSfzh()) && StringUtils.isBlank(jzzp.getXm())) {
                    continue;
                }
                Fn35DTO fn35 = ServiceUtils.getInstance().getFn35(jzzp.getSfzh(), jzzp.getXm());
                logUtil.append(StrUtil.format("fn35.isSucc:{}", fn35.isSucc()));
                if (!fn35.isSucc()) {
                    continue;
                }
                FeatrueDTO fn36 = ServiceUtils.getInstance().getFn36(jzzp.getSfzh(), jzzp.getXm(), fn35.latest().getImgIdx());
                logUtil.append(StrUtil.format("fn36.isSucc:{}", fn35.isSucc()));
                if (!fn36.isSucc()) {
                    continue;
                }
                Jzzptzz jzzptzz = new Jzzptzz();
                jzzptzz.setZpid(jzzp.getZpid());
                jzzptzz.setZptzz(fn36.getFeatureStr());
                jzzptzz.setBbh(fn36.getBbh());
                jzzp.setFzzd(1);
                jzzp.setZplj(fn35.latest().getArchiveKey());
                this.updateById(jzzp);
                jzzptzzService.saveOrUpdate(jzzptzz);
                logUtil.append(StrUtil.format("upload photo success"));
            } catch (Exception e) {
                logUtil.setSucc(false).append(LogUtils.getTrace(e));
            } finally {
                logUtil.append(StrUtil.format("cost.time={}", started.elapsed(TimeUnit.MILLISECONDS)));
                if (logUtil.isSucc()) {
                    log.info(logUtil.getLogString());
                } else {
                    log.error(logUtil.getLogString());
                }
            }
        }
    }
}
