package com.tor.project.utils;

import cn.com.itsea.hldfs.api.ArchiveInfoData;
import cn.com.itsea.hldfs.api.CallResultOfHlDfs;
import cn.com.itsea.hldfs.api.HlDfsArchiveService;
import cn.com.itsea.hldfs.api.client.FactoryOfTrackerServerClientSide;
import cn.com.itsea.hldfs.api.client.HldfsArchiveServiceImpl;
import cn.com.itsea.hldfs.api.client.request.archive.ResponseResultOfArchiveDownload;
import cn.com.itsea.hldfs.define.ArchivePathOfHlDfs;
import cn.com.itsea.util.FormatedLogAppender;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicReference;

@Component
@Slf4j
public class FileStorager {

    private static HlDfsArchiveService archiveService = null;

    public static synchronized void init() {
        if (archiveService == null) {
            archiveService =
                    new HldfsArchiveServiceImpl(false, FactoryOfTrackerServerClientSide.getInstance().getPublicKeyLocalStore(),
                            FactoryOfTrackerServerClientSide.getInstance().getMachineId(),
                            FactoryOfTrackerServerClientSide.getInstance().getTrackerListPreConfiged(),
                            FactoryOfTrackerServerClientSide.getInstance().createConfigOfServerAccess(), null);
        }
    }

    public static String upload(byte[] bytes) {
        FormatedLogAppender logger = new FormatedLogAppender();
        boolean procFail = false;
        try {
            if (archiveService == null) {
                init();
            }
            AtomicReference<ArchiveInfoData> atomicReference = new AtomicReference<>();
            CallResultOfHlDfs callResultOfHlDfs = archiveService.saveArchive(bytes, "dat", logger, 60, atomicReference, false, new AtomicReference<Long>());
            if (callResultOfHlDfs.isSucc() && null != atomicReference.get()) {
                // getURIPath结构类似[hldfs.v[volume].s[sid].theday.suffix.dat,xxxxxxxxx] 需要的是 hldfs.v[volume].s[sid].theday.suffix.dat
                return atomicReference.get().getMyKey().getURIPath().substring(1).split(",")[0];
            } else {
                procFail = true;
            }
        } catch (Throwable e) {
            logger.append("upload archive failed", e);
            procFail = true;
        } finally {
            if (procFail) {
                LoggerFactory.getLogger("error").error(logger.toString());
            } else {
                LoggerFactory.getLogger("root").info(logger.toString());
            }
        }
        return "";
    }

    /**
     * 暂时不明确 append 方法作用,项目中也并未使用到
     *
     * @param path
     * @param bytes
     * @param offset
     * @param length
     * @return
     */
    public int append(String path, byte[] bytes, int offset, int length) {
        return 0;
    }

    public static byte[] download(String uriPath) {
        if (archiveService == null) {
            init();
        }
        if (checkPath(uriPath)) {
            return null;
        }
        // 信息中心分布式存储（正式环境） 需要把.s1.改成.s0.
        /*uriPath = uriPath.replaceAll("s1", "s0");*/
        FormatedLogAppender logger = new FormatedLogAppender();
        boolean procFail = false;
        try {
            AtomicReference<ResponseResultOfArchiveDownload> refArchiveData = new AtomicReference<>();
            CallResultOfHlDfs callResultOfHlDfs = archiveService.loadArchive(new ArchivePathOfHlDfs(uriPath), logger,
                    60, refArchiveData, new AtomicReference<Long>());
            if (callResultOfHlDfs.isSucc() && null != refArchiveData.get()) {
                return refArchiveData.get().getSrcData();
            } else {
                procFail = true;
                logger.append(" FileStorager download failed ");
            }
        } catch (Throwable e) {
            logger.append("down archive failed,unexpected exception", e);
            procFail = true;
        } finally {
            if (procFail) {
                LoggerFactory.getLogger("error").error(logger.toString());
            } else {
                LoggerFactory.getLogger("root").info(logger.toString());
            }
        }
        return null;
    }

    public static int delete(String path) {
        if (archiveService == null) {
            init();
        }
        if (checkPath(path)) {
            return -1;
        }
        FormatedLogAppender logger = new FormatedLogAppender();
        boolean procFail = false;
        try {
            CallResultOfHlDfs callResultOfHlDfs = archiveService.removeArchive(new ArchivePathOfHlDfs(path), logger, 60, true, new AtomicReference<Long>());
            if (callResultOfHlDfs.isSucc()) {
                return 0;
            } else {
                procFail = true;
                logger.append(" FileStorager delete failed ");
            }
        } catch (Exception e) {
            logger.append("delete archive failed,unexpected exception", e);
            procFail = true;
        } finally {
            if (procFail) {
                LoggerFactory.getLogger("error").error(logger.toString());
            } else {
                LoggerFactory.getLogger("root").info(logger.toString());
            }
        }
        return -1;
    }

    private static boolean checkPath(String path) {
        return StringUtils.isBlank(path) || !path.startsWith("hldfs.");
    }

    /*public static void main(String[] args) {
        SpzpjkSyncApplication.initContext();
        String path1 = "hldfs.v100.s101.20200617.10000016e072a793f.dat";
        String path2 = "hldfs.v100.s101.20200617.10000016e072a7940.dat";
        byte[] bytes1 = FileStorager.download(path1);
        byte[] bytes2 = FileStorager.download(path2);
        CompareFeatureDTO compareFeatureDTO = ServiceUtils.getInstance().compareFeature(bytes1, bytes2);

        String path3 = "hldfs.v100.s101.20200716.10000016e072a9186.dat";
        int delete = FileStorager.delete(path3);
        byte[] bytes3 = FileStorager.download(path3);
        System.out.println(compareFeatureDTO.toString());

    }*/
}