package com.tor.config;

import cn.com.itsea.hldfs.api.client.FactoryOfTrackerServerClientSide;
import cn.com.itsea.hldfs.server.utils.EncryptUtil;
import com.tor.utils.FileStorager;
import com.tor.utils.ServiceUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author Tzx
 */
@Slf4j
@Component
public class HlServiceConfig implements CommandLineRunner {

    @Override
    public void run(String... strings) throws Exception {
        this.init();
    }

    public void init(){
        boolean isSuccStart = true;
        while (isSuccStart) {
            try {
                //项目根路径
                String path = System.getProperty("user.dir");
                // 注册服务
                File fileConfigXml = new File(path + "/cfg/hldfs.client.conf.xml");
                FactoryOfTrackerServerClientSide.getInstance().InitialContext(fileConfigXml);
                // 初始化加密工具
                fileConfigXml = new File(path + "/cfg/cert.key.server.xml");
                EncryptUtil.getInstance().InitialContext(fileConfigXml);
                // 存储服务初始化
                FileStorager.init();
                // app服务初始化
                ServiceUtils.InitialContext();
                isSuccStart = !isSuccStart;
            } catch (Exception e) {
                log.error(e.getMessage());
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    log.error(ex.getMessage());
                }
            }
        }
    }
}
