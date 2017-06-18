package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;


public interface UserDAO extends BaseDAO<User> {
    //模糊查询----管理员
    List<User> queryLike(@Param("type") String type, @Param("content") String content);

    //根据手机号查一个----登陆时
    User queryByPhone(String phone);
    //查询在某时间到某一时间的注册人数
    Integer countRegist(@Param("start")Date start,@Param("end")Date end);
}
