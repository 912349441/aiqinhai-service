/**
 * 定时任务
 */
package com.tor.project.timetask;

import cn.hutool.core.thread.ThreadUtil;
import com.tor.project.service.JzzpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
@PropertySource("classpath:application.yml")
public class MyJob {

    @Autowired
    private JzzpService jzzpService;

    @Scheduled(cron = "${myJob.jobs-schedule}")
    public void execute() throws Exception {
        /*ThreadUtil.execute(()->jzzpService.migrateJzppHyPhotosJob());
        ThreadUtil.execute(()->jzzpService.migrateJzppJhPhotosJob());*/
    }

}
