package com.tor.project.service.impl;

import com.tor.generator.core.AbstractService;
import com.tor.project.entity.Resources;
import com.tor.project.mapper.ResourcesMapper;
import com.tor.project.service.ResourcesService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Tzx on 2019/07/11.
 */
@Service
@Transactional
public class ResourcesServiceImpl extends AbstractService<Resources> implements ResourcesService {
    @Resource
    private ResourcesMapper sysResourcesMapper;

}
