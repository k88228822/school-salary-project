package com.yufeimen.application.service;

import com.yufeimen.application.mapper.UserMapper;
import com.yufeimen.application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseService {
    @Autowired
    UserMapper userMapper;

    public User checkNameAndPassword(String name, String password) {
        List<User> users = userMapper.selectByName(name);
        if (users.size() == 0) throw new RuntimeException("不存在的用户");
        if (!users.get(0).getPassword().equals(password)) throw new RuntimeException("密码错误");
        return users.get(0);
    }
}
