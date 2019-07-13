package com.tor.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.tor.generator.core.AbstractService;
import com.tor.project.entity.User;
import com.tor.project.entity.UserRole;
import com.tor.project.mapper.UserMapper;
import com.tor.project.mapper.UserRoleMapper;
import com.tor.project.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by Tzx on 2019/07/11.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public PageInfo<User> selectByPage(User user, int start, int length) {
        int page = start/length+1;
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (StringUtil.isNotEmpty(user.getUsername())) {
            criteria.andLike("username", "%" + user.getUsername() + "%");
        }
        if (user.getId() != null) {
            criteria.andEqualTo("id", user.getId());
        }
        if (user.getStatus() != null) {
            criteria.andEqualTo("status", user.getStatus());
        }
        //分页查询
        PageHelper.startPage(page, length);
        List<User> userList = userMapper.selectByCondition(example);
        return new PageInfo<>(userList);
    }

    @Override
    public User selectByUsername(String username) {
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("username",username);
        List<User> users = userMapper.selectByCondition(example);
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }

    @Override
    @Transactional(propagation= Propagation.REQUIRED,readOnly=false,rollbackFor={Exception.class})
    public void delUser(Integer userid) {
        //删除用户表
        mapper.deleteByPrimaryKey(userid);
        //删除用户角色表
        Example example = new Example(UserRole.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userid",userid);
        userRoleMapper.deleteByCondition(example);
    }
}
