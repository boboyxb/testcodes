package com.baizhi.fifth.service.impl;

import com.baizhi.fifth.dao.AddressDAO;
import com.baizhi.fifth.dao.UserDAO;
import com.baizhi.fifth.entity.Address;
import com.baizhi.fifth.entity.User;
import com.baizhi.kfsy.util.SaltUtils;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Component("userServiceWS")
public class UserServiceWS {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private AddressDAO addressDAO;

    public User login(String phone,String password){
        User userDB = userDAO.queryByPhone(phone);
        if(userDB==null)throw new RuntimeException("该用户不存在");
        if(!userDB.getPassword().equals(password))throw new RuntimeException("密码不正确");
        return userDB;
    }
    public String verifyCode(String phone){
        User userDB = userDAO.queryByPhone(phone);
        if(userDB!=null)throw new RuntimeException("该用户已存在,请重新注册");
        //发送验证码
        String url = "http://gw.api.taobao.com/router/rest";
        String appkey = "23756229";
        String secret = "3fed1fb0441771883872eeb1066bc4ad";

        TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
        AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
        req.setExtend("123456");
        req.setSmsType("normal");
        req.setSmsFreeSignName("Yuxb啵啵");
        String salt = SaltUtils.getSalt(4);
        req.setSmsParamString("{\"code\":\""+salt+"\",\"name\":\"哈哈\"}");
        req.setRecNum(phone);
        req.setSmsTemplateCode("SMS_62265277");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
        return salt;
    }
    public User regist(User user){
        //设置id
        user.setId(UUID.randomUUID().toString());
        user.setStatus("已激活");
        user.setRegistDate(new Date());
        userDAO.insert(user);
        return user;
    }
    public void update(User user){
        userDAO.update(user);
    }
    //查询用户的所有收货地址
    public ArrayList<Address> queryAddressByUser(String userId){
        ArrayList<Address> addresses = (ArrayList<Address>) addressDAO.queryByUserId(userId);
        return addresses;
    }
    //给用户添加收货地址
    public void insertAddress(String userId,Address address){
        User user=new User();
        user.setId(userId);
        address.setUser(user);
        addressDAO.insert(address);

    }
    //删除收货地址
    public void deleteAddress(String addressId){
        addressDAO.delete(addressId);
    }
    //修改收货地址
    public void updateAddress(Address address){
        addressDAO.update(address);
    }
}
