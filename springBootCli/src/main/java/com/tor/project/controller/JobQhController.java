package com.tor.project.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.tor.project.service.HyJzzpPhotoService;
import com.tor.project.service.JzzpService;
import com.tor.project.utils.RegVerUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/job/qh")
public class JobQhController {

    @Autowired
    private JzzpService jzzpService;

    @GetMapping("/migrateJzppInfo")
    @ApiModelProperty("青海 - 人员信息同步")
    public void migrateJzppInfo(HttpServletRequest request, HttpServletResponse response) {
        ThreadUtil.execute(() -> {
            jzzpService.migrateQhJzppInfo();
        });
    }

    @GetMapping("/migrateQhLdjgInfo")
    @ApiModelProperty("青海 - 两定机构")
    public void migrateQhLdjgInfo(HttpServletRequest request, HttpServletResponse response) {
        ThreadUtil.execute(() -> {
            jzzpService.migrateQhLdjgInfo();
        });
    }

    @GetMapping("/migrateQhZybrInfo")
    @ApiModelProperty("青海 - 入院")
    public void migrateQhZybrInfo(HttpServletRequest request, HttpServletResponse response) {
        ThreadUtil.execute(() -> {
            jzzpService.migrateQhZybrInfo();
        });
    }

    @GetMapping("/migrateQhJzppPhotos")
    @ApiModelProperty("青海 - 照片同步到生物特征平台")
    public void migrateQhJzppPhotos() {
        ThreadUtil.execute(() -> {
            jzzpService.migrateQhJzppPhotos();
        });
    }

}
