package com.yufeimen.application.service;

import com.yufeimen.application.mapper.SalaryMapper;
import com.yufeimen.application.mapper.UserMapper;
import com.yufeimen.application.model.Salary;
import com.yufeimen.application.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    BaseService baseService;
    @Autowired
    SalaryMapper salaryMapper;
    @Autowired
    UserMapper userMapper;

    public List<Salary> getSalary(int id){
        Salary salary=salaryMapper.selectByPrimaryKey(id);
        List<Salary> list=new ArrayList<>();
        list.add(salary);
        return list;
    }

    public int updatePassword(String name , String password, String newPassword){
        User user=baseService.checkNameAndPassword(name,password);
        user.setPassword(newPassword);
        return userMapper.updateByPrimaryKey(user);
    }

    public User login(String name,String password){
        return baseService.checkNameAndPassword(name,password);
    }

}
