package com.tor.project.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.tor.project.service.HyJzzpPhotoService;
import com.tor.project.service.JzzpService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job/hy")
public class JobHyController {

    @Autowired
    private JzzpService jzzpService;

    @Autowired
    private HyJzzpPhotoService jzzpPhotoService;

    @GetMapping("/migrateJzppPhotos")
    @ApiModelProperty("海盐 - 照片同步到生物特征平台")
    public void migrateJzppPhotos() {
        ThreadUtil.execute(() -> {
            jzzpService.migrateJzppHyInfoJob();
            jzzpService.migrateJzppHyPhotosJob();
        });
    }

}
