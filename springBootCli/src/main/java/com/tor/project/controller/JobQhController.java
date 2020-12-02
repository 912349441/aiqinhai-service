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
    @ApiModelProperty("青海 - 信息同步到生物特征平台")
    public void migrateJzppInfo(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("http://192.168.1.2:8092/spzpjk/hospital/hospital-patient-list.html").forward(request, response);
            log.info(response.toString());
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ThreadUtil.execute(() -> {
            jzzpService.migrateQhJzppInfo();
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
