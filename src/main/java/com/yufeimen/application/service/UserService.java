package com.yufeimen.application.service;

import com.yufeimen.application.mapper.SalaryMapper;
import com.yufeimen.application.mapper.UserMapper;
import com.yufeimen.application.model.Salary;
import com.yufeimen.application.model.User;
import com.yufeimen.application.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        baseService.checkNameAndPassword(name,password);
        List<User> users = userMapper.selectByName(name);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        users.get(0).setPassword(encoder.encode(newPassword));
        return userMapper.updateByPrimaryKey(users.get(0));
    }

    public List<Salary> getSalaryDates(String usercode){
        Map map=new HashMap<String,Object>();
        map.put("date",DateUtil.formatDate(DateUtil.getCurrYearFirst()));
        map.put("usercode",Integer.parseInt(usercode));
        return  salaryMapper.selectDataByYear(map);
    }

}
