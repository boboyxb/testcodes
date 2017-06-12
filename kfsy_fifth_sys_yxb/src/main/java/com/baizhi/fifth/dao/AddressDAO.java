package com.baizhi.fifth.dao;

import com.baizhi.fifth.entity.Address;

import java.util.List;

public interface AddressDAO extends BaseDAO<Address> {
    //根据用户id查多个地址
    List<Address> queryByUserId(String id);
}
