package com.tor.project.timetask;

import cn.hutool.core.thread.ThreadUtil;
import com.tor.project.service.JzzpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.yml")
public class MigrateJyZybrInfo {
    @Autowired
    private JzzpService jzzpService;

    @Scheduled(cron = "${JyJob.MigrateJyZybrInfo}")
    public void execute() throws Exception {
        ThreadUtil.execute(()->jzzpService.migrateJyJzppInfo());
    }
}
