package com.tor.project.service.impl;

import com.tor.project.mapper.UserMapper;
import com.tor.project.entity.User;
import com.tor.project.service.UserService;
import com.tor.generator.core.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by Tzx on 2019/07/11.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper sysUserMapper;

}
