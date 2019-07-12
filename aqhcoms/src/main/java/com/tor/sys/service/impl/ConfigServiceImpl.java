package com.tor.sys.service.impl;

import com.tor.sys.entity.Config;
import com.tor.sys.mapper.ConfigMapper;
import com.tor.sys.service.ConfigService;
import com.tor.generator.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Tzx on 2019/07/11.
 */
@Service
@Transactional
public class ConfigServiceImpl extends AbstractService<Config> implements ConfigService {
    @Resource
    private ConfigMapper sysConfigMapper;
}
