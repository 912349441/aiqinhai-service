package com.tor.project.service.impl;

import com.tor.project.mapper.RoleResourcesMapper;
import com.tor.project.entity.RoleResources;
import com.tor.project.service.RoleResourcesService;
import com.tor.generator.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Tzx on 2019/07/11.
 */
@Service
@Transactional
public class RoleResourcesServiceImpl extends AbstractService<RoleResources> implements RoleResourcesService {
    @Resource
    private RoleResourcesMapper sysRoleResourcesMapper;

}
