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
@RequestMapping("/job/jy")
public class JobJyController {

    @Autowired
    private JzzpService jzzpService;

    @Autowired
    private HyJzzpPhotoService jzzpPhotoService;

    @GetMapping("/migrateJzppPhotos")
    @ApiModelProperty("江阴 - 照片同步到生物特征平台")
    public void migrateJzppPhotos() {
        ThreadUtil.execute(() -> jzzpService.migrateJzppJyPhotosJob());
    }

    @GetMapping("/migrateJyJzppInfo()")
    @ApiModelProperty("江阴 - 信息同步到生物特征平台")
    public void migrateJyJzppInfo() {
        ThreadUtil.execute(() -> jzzpService.migrateJyJzppInfo());
    }

}
