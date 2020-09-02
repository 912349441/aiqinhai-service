package com.tor.project.controller;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ThreadUtil;
import com.mysql.jdbc.Blob;
import com.tor.common.utils.ThreadPoolUtils;
import com.tor.project.entity.HyJzzpPhoto;
import com.tor.project.service.HyJzzpPhotoService;
import com.tor.project.service.JzzpService;
import com.tor.project.utils.FileStorager;
import io.swagger.annotations.ApiModelProperty;
import org.apache.ibatis.type.JdbcType;
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
        ThreadUtil.execute(() -> jzzpService.migrateJzppHyPhotosJob());
    }

}
