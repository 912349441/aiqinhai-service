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
public class QhPhotosJob {

    @Autowired
    private JzzpService jzzpService;

    @Scheduled(cron = "${QhJob.QhPhotosJob}")
    public void execute() {
        ThreadUtil.execute(() -> jzzpService.migrateQhJzppPhotos());
    }

}
