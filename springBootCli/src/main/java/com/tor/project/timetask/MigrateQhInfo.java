package com.tor.project.timetask;

import cn.hutool.core.thread.ThreadUtil;
import com.tor.project.service.JzzpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@PropertySource("classpath:application.yml")
@EnableScheduling
public class MigrateQhInfo {
    @Autowired
    private JzzpService jzzpService;

    @Scheduled(cron = "${QhJob.MigrateQhInfo}")
    public void execute() throws Exception {
        /*ThreadUtil.execute(() -> {
            jzzpService.migrateQhJzppInfo();
            jzzpService.migrateQhZybrInfo();
            jzzpService.migrateQhZybrCyInfo();
        });
        ThreadUtil.execute(() -> jzzpService.migrateQhLdjgInfo());
        ThreadUtil.execute(() -> jzzpService.migrateQhZybrInfo());
        ThreadUtil.execute(() -> jzzpService.migrateQhJzppPhotos());*/
    }

}
