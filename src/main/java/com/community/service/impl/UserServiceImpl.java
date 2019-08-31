package com.community.service.impl;

import com.community.mapper.UserMapper;
import com.community.model.User;
import com.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addOrUpdate(User user) {
        User accountUser=userMapper.findByAccountId(user.getAccountId());
        if(accountUser==null){
            //插入
            userMapper.insert(user);
        } else{
            //更新
            accountUser.setToken(user.getToken());
            userMapper.update(accountUser);
        }
    }
}
