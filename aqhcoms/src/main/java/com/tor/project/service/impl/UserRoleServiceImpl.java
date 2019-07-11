package com.tor.project.service.impl;

import com.tor.project.mapper.UserRoleMapper;
import com.tor.project.entity.UserRole;
import com.tor.project.service.UserRoleService;
import com.tor.generator.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Tzx on 2019/07/11.
 */
@Service
@Transactional
public class UserRoleServiceImpl extends AbstractService<UserRole> implements UserRoleService {
    @Resource
    private UserRoleMapper sysUserRoleMapper;

}
