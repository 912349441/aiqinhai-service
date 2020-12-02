package com.tor.project.config;

import cn.com.itsea.faceservice.api.HlFaceWebserviceApiService;
import cn.com.itsea.faceservice.hldfs.impl.HlFaceWevserviceApiServiceImpl;
import cn.com.itsea.hldfs.api.HlDfsArchiveService;
import cn.com.itsea.hldfs.api.HlDfsTrackerService;
import cn.com.itsea.hldfs.api.client.FactoryOfTrackerServerClientSide;
import cn.com.itsea.hldfs.api.client.HldfsArchiveServiceImpl;
import cn.com.itsea.hldfs.api.client.HldfsTrackerServiceImpl;
import cn.com.itsea.hldfs.server.utils.EncryptUtil;
import com.tor.project.utils.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.File;

/**
 * @author zhuhs
 * @date 2020/9/27 8:28
 */
@Configuration
@Slf4j
public class HlServiceConfig {

    @Bean
    public FactoryOfTrackerServerClientSide factoryOfTrackerServerClientSide() {
        FactoryOfTrackerServerClientSide instance = FactoryOfTrackerServerClientSide.getInstance();
        try {
            while (true){
                String userDir = System.getProperty("user.dir");
                File file = new File(userDir + "/cfg/hldfs.client.conf.xml");
                if(!file.exists()){
                    log.warn("file not found ",file.getPath());
                }
                instance.InitialContext(new File(userDir + "/cfg/hldfs.client.conf.xml"));
                file = new File(userDir + "/cfg/cert.key.server.xml");
                if(!file.exists()){
                    log.warn("file not found ",file.getPath());
                }
                EncryptUtil.getInstance().InitialContext(new File(userDir + "/cfg/cert.key.server.xml"));
                ServiceUtils.initialContext();
                break;
            }
        } catch (Exception e) {
            throw new RuntimeException("HlServiceConfig initialization failed\n\tat ", e);
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
                trackerServer.createConfigOfServerAccess(), null);
    }

}
