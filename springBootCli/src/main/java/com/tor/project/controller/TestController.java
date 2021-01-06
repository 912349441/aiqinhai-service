package com.tor.project.controller;


import cn.hutool.core.lang.Console;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 * 住院病人表 前端控制器
 * </p>
 *
 * @author Tzx
 * @since 2020-12-03
 */
@Slf4j
@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired

    @RequestMapping("/handlingAttendanceEffectively")
    public void migrateJzppInfo(HttpServletRequest request, HttpServletResponse response) {

    }

}

