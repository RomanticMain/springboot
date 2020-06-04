package com.wtbu.day01springdemo.service;

import com.wtbu.day01springdemo.dao.UserMapper;
import com.wtbu.day01springdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    public boolean registUser(User tempUser) {
        return userMapper.registUser(tempUser);
    }
}
