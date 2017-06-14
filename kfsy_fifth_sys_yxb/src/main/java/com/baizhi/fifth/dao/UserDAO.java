package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.User;


public interface UserDAO extends BaseDAO<User> {
    //根据用户名查一个----管理员
    User queryByName(String name);

    //根据手机号查一个----登陆时
    User queryByPhone(String phone);
}
