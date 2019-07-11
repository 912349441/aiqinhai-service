package com.tor.project.service.impl;

import com.tor.generator.core.AbstractService;
import com.tor.project.entity.TczActivity;
import com.tor.project.mapper.TczActivityMapper;
import com.tor.project.service.TczActivityService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Tzx on 2019/04/06.
 */
@Service
@Transactional
public class TczActivityServiceImpl extends AbstractService<TczActivity> implements TczActivityService {
    @Resource
    private TczActivityMapper tczActivityMapper;

}
