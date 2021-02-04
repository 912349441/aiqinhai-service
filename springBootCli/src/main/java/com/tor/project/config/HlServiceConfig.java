package com.tor.project.config;

import cn.com.itsea.faceservice.api.HlFaceWebserviceApiService;
import cn.com.itsea.faceservice.api.PermissionAccessParamOfFaceService;
import cn.com.itsea.faceservice.hldfs.impl.HlFaceWevserviceApiServiceImpl;
import cn.com.itsea.hldfs.api.HlDfsArchiveService;
import cn.com.itsea.hldfs.api.HlDfsTrackerService;
import cn.com.itsea.hldfs.api.client.FactoryOfTrackerServerClientSide;
import cn.com.itsea.hldfs.api.client.HldfsArchiveServiceImpl;
import cn.com.itsea.hldfs.api.client.HldfsTrackerServiceImpl;
import cn.com.itsea.hldfs.server.utils.EncryptUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import com.tor.project.utils.FormatedLogUtil;
import com.tor.project.utils.LogUtils;
import com.tor.project.utils.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;

/**
 * @author Tzx
 * @date 2020/9/27 8:28
 */
@Slf4j
@Configuration
public class HlServiceConfig {

    @Value("${hl.hldfs.acc-code}")
    private String accCode;
    @Value("${hl.hldfs.access-key}")
    private String accessKey;

    @Bean
    public FactoryOfTrackerServerClientSide factoryOfTrackerServerClientSide() {
        FactoryOfTrackerServerClientSide instance = FactoryOfTrackerServerClientSide.getInstance();
        FormatedLogUtil logUtil = null;
        try {
            while (true) {
                logUtil = new FormatedLogUtil();
                String userDir = System.getProperty("user.dir");
                File file = new File(userDir + "/cfg/hldfs.client.conf.xml");
                if (!file.exists()) {
                    log.warn("file not found ", file.getPath());
                    continue;
                }
                instance.InitialContext(new File(userDir + "/cfg/hldfs.client.conf.xml"));
                logUtil.append("FactoryOfTrackerServerClientSide InitialContext success");
                file = new File(userDir + "/cfg/cert.key.server.xml");
                if (!file.exists()) {
                    log.warn("file not found ", file.getPath());
                    continue;
                }
                EncryptUtil.getInstance().InitialContext(new File(userDir + "/cfg/cert.key.server.xml"));
                logUtil.append("EncryptUtil InitialContext success");
                ServiceUtils.initialContext();
                log.info(logUtil.append("ServiceUtils InitialContext success").getLogString());
                break;
            }
        } catch (Exception e) {
            log.error(logUtil.setSucc(false).append(LogUtils.getTrace(e)).getLogString());
            throw new RuntimeException("HlServiceConfig initialization failed\n\tat " + LogUtils.getTrace(e));
        }
        return instance;
    }

    @Bean
    public HlDfsTrackerService hlDfsTrackerService() {
        FactoryOfTrackerServerClientSide trackerServer = factoryOfTrackerServerClientSide();
        return new HldfsTrackerServiceImpl(
                trackerServer.getPublicKeyLocalStore(),
                trackerServer.getMachineId(),
                trackerServer.getTrackerListPreConfiged(),
                trackerServer.createConfigOfServerAccess(),
                null
        );
    }

    @Bean
    public HlDfsArchiveService hlDfsArchiveService() {
        FactoryOfTrackerServerClientSide trackerServer = factoryOfTrackerServerClientSide();
        return new HldfsArchiveServiceImpl(false,
                trackerServer.getPublicKeyLocalStore(),
                trackerServer.getMachineId(),
                trackerServer.getTrackerListPreConfiged(),
                trackerServer.createConfigOfServerAccess(),
                null);
    }

    @Bean
    public HlFaceWebserviceApiService hlFaceWebserviceApiService() {
        FactoryOfTrackerServerClientSide trackerServer = factoryOfTrackerServerClientSide();
        return new HlFaceWevserviceApiServiceImpl(false,
                trackerServer.getPublicKeyLocalStore(),
                trackerServer.getMachineId(),
                trackerServer.getTrackerListPreConfiged(),
                trackerServer.createConfigOfServerAccess(), null,
                permissionAccessParamOfFaceService());
    }

    @Bean
    public PermissionAccessParamOfFaceService permissionAccessParamOfFaceService() {
        return new PermissionAccessParamOfFaceService(accCode, accessKey);
    }

}
