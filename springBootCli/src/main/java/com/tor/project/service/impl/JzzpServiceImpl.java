package com.tor.project.service.impl;

import java.time.LocalDateTime;

import cn.com.itsea.util.ImageUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.StopWatch;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.base.Stopwatch;
import com.sun.javafx.scene.control.behavior.OptionalBoolean;
import com.tor.project.dto.FeatrueDTO;
import com.tor.project.entity.HyJzzpPhoto;
import com.tor.project.entity.Jzzp;
import com.tor.project.entity.JzzpTemplateZptzz;
import com.tor.project.entity.Jzzptzz;
import com.tor.project.mapper.primary.JzzpMapper;
import com.tor.project.service.JzzpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tor.project.service.JzzpTemplateZptzzService;
import com.tor.project.service.JzzptzzService;
import com.tor.project.utils.FileStorager;
import com.tor.project.utils.FormatedLogUtil;
import com.tor.project.utils.LogUtils;
import com.tor.project.utils.ServiceUtils;
import io.swagger.models.auth.In;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;

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

    @Autowired
    private JzzptzzService jzzptzzService;
    @Autowired
    private JzzpTemplateZptzzService jzzpTemplateZptzzService;

    private final static Lock lock = new ReentrantLock();

    /**
     * 金华迁移照片
     */
    @Override
    public void migrateJzppJhPhotosJob() {
        FormatedLogUtil logUtil = new FormatedLogUtil();
        Stopwatch started = null;
        lock.lock();
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
            lock.unlock();
            logUtil.append(StrUtil.format("释放锁,cost.time={}",started.elapsed(TimeUnit.MILLISECONDS)));
            log.info(logUtil.getLogString());
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
                    logUtil.append(StrUtil.format("zplj:{}", jzzp.getZplj()));
                    byte[] bytes = getPhotosByte(jzzp.getZplj());
                    if (null == bytes || bytes.length < 1) {
                        logUtil.append("photos byte is null");
                    } else {
                        FeatrueDTO featrueDTO = ServiceUtils.getInstance().extractImageFeature(bytes);
                        if(!featrueDTO.isSucc()){
                            logUtil.append("无法提取特征值");
                        }else {
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
                    logUtil.append(StrUtil.format("lszplj:{}", jzzp.getLszplj()));
                    byte[] bytes = getPhotosByte(jzzp.getLszplj());
                    if (null == bytes || bytes.length < 1) {
                        logUtil.append("photos byte is null");
                    } else {
                        FeatrueDTO featrueDTO = ServiceUtils.getInstance().extractImageFeature(bytes);
                        if(!featrueDTO.isSucc()){
                            logUtil.append("无法提取特征值");
                        }else {
                            JzzpTemplateZptzz jzzpTemplateZptzz = new JzzpTemplateZptzz();
                            jzzpTemplateZptzz.setZpid(jzzp.getZpid());
                            jzzpTemplateZptzz.setZptzz(featrueDTO.getFeatureStr());
                            jzzpTemplateZptzz.setBbh(featrueDTO.getBbh());
                            String newZpljPaht = FileStorager.upload(bytes);
                            if (StringUtils.isBlank(newZpljPaht)) {
                                logUtil.append("upload failed");
                            } else {
                                status = 1;
                                jzzp.setLszplj(newZpljPaht);
                            }
                            jzzpTemplateZptzzService.saveOrUpdate(jzzpTemplateZptzz);
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


    /**
     * 海盐迁移照片
     */
    @Override
    public void migrateJzppHyPhotosJob() {
        FormatedLogUtil logUtil = new FormatedLogUtil();
        Stopwatch started = null;
        lock.lock();
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
                migrateJzppHyPhotos(jzzp.getThreadNumber());
                countDownLatch.countDown();
            }));
            countDownLatch.await();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
            logUtil.append(StrUtil.format("释放锁,cost.time={}",started.elapsed(TimeUnit.MILLISECONDS)));
            log.info(logUtil.getLogString());
        }
    }

    private void migrateJzppHyPhotos(int num) {
        QueryWrapper<Jzzp> wrapper = new QueryWrapper<Jzzp>()
                .select("ZPID,SFZH,XM")
                .eq("THREAD_NUMBER", num)
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
                List<HyJzzpPhoto> jzzpPhotos = baseMapper.getHyJzzpZpBlobBySfzhAndXm(jzzp.getSfzh(), jzzp.getXm());
                if (CollectionUtil.isEmpty(jzzpPhotos)) {
                    continue;
                }
                HyJzzpPhoto jzzpPhoto = jzzpPhotos.get(0);
                if (null == jzzpPhoto.getZp() || jzzpPhoto.getZp().length < 1) {
                    continue;
                }
                logUtil.append(StrUtil.format("have found jzzpPhoto"));
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
                if (StringUtils.isBlank(jzzpPhotoPath)) {
                    continue;
                }
                jzzp.setFzzd(1);
                jzzp.setZplj(jzzpPhotoPath);
                this.updateById(jzzp);
                jzzptzzService.saveOrUpdate(jzzptzz);
                logUtil.append(StrUtil.format("upload photo succ"));
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
