package com.tor.project.timetask;

import cn.hutool.core.thread.ThreadUtil;
import com.tor.project.utils.RegVerUtils;
import com.tor.project.service.JzzpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
@EnableScheduling
public class MigrateJzppHyInfoAndPhotosJob {
    @Autowired
    private JzzpService jzzpService;

    @Scheduled(cron = "${HyJob.MigrateJzppHyInfoAndPhotosJob}")
    public void execute() throws Exception {
        ThreadUtil.execute(() -> {
            jzzpService.migrateJzppHyInfoJob();
            jzzpService.migrateJzppHyPhotosJob();
        });
    }
}
