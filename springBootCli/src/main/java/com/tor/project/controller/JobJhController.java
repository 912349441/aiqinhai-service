package com.tor.project.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.tor.project.service.JzzpService;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/job/jh")
public class JobJhController {

    @Autowired
    private JzzpService jzzpService;

    @GetMapping("/migrateJzppPhotos")
    @ApiModelProperty("金华 - 迁移参保人员照片作业")
    public void migrateJzppPhotos() {
        ThreadUtil.execute(() -> jzzpService.migrateJzppJhPhotosJob());
    }

}
