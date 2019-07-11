package com.tor.project.service.impl;

import com.tor.project.mapper.RoleMapper;
import com.tor.project.entity.Role;
import com.tor.project.service.RoleService;
import com.tor.generator.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Tzx on 2019/07/11.
 */
@Service
@Transactional
public class RoleServiceImpl extends AbstractService<Role> implements RoleService {
    @Resource
    private RoleMapper sysRoleMapper;

}
