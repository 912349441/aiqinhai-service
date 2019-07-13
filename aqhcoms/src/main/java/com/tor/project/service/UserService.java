package com.tor.project.service;

import com.github.pagehelper.PageInfo;
import com.tor.generator.core.Service;
import com.tor.project.entity.User;


/**
 * Created by Tzx on 2019/07/11.
 */
public interface UserService extends Service<User> {
    PageInfo<User> selectByPage(User user, int start, int length);

    User selectByUsername(String username);

    void delUser(Integer userid);
}
