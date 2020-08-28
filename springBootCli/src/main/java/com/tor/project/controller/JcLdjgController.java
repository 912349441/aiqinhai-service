package com.tor.project.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tor.project.entity.JcLdjg;
import com.tor.project.entity.Jzzp;
import com.tor.project.service.JcLdjgService;
import com.tor.project.service.JzzpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 两定机构信息表 前端控制器
 * </p>
 *
 * @author Tzx
 * @since 2020-08-27
 */
@RestController
@RequestMapping("/jcLdjg")
public class JcLdjgController {
    @Autowired
    private JcLdjgService ldjgService;

    @GetMapping("/list")
    public List<JcLdjg> list(){
        QueryWrapper<JcLdjg> wrapper = new QueryWrapper<>();
        List<JcLdjg> list = ldjgService.list(wrapper);
        return list;
    }
}

