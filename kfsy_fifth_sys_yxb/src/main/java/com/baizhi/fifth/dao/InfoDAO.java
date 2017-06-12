package com.baizhi.fifth.dao;


import com.baizhi.fifth.entity.Info;

import java.util.List;

public interface InfoDAO extends BaseDAO<Info> {
    //根据订单id查询评价
    Info queryByOrderId(String id);
    //根据用户查询评价
    List<Info> queryByUserId(String id);
    //根据用户名字查询评价
    List<Info> queryByUserName(String name);
    //查询特定内容的评价数量
    Integer countLikeInfo(String content);
}
