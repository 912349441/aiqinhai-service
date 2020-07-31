package com.tor.project.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tor.project.entity.Jzzp;
import com.tor.project.service.JzzpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Wrapper;
import java.util.List;

/**
 * <p>
 * 参保人员信息表-基准照片表 前端控制器
 * </p>
 *
 * @author Tzx
 * @since 2020-07-31
 */
@Controller
@RequestMapping("/jzzp")
public class JzzpController {

    @Autowired
    private JzzpService jzzpService;

    @ResponseBody
    @GetMapping("/get")
    public String get(){
        List<Jzzp> list = jzzpService.list(null);
        return JSONObject.toJSONString(list);
    }

}

