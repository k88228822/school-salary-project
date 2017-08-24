package com.yufeimen.application.controller;

import com.yufeimen.application.model.Salary;
import com.yufeimen.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/login")
    public Object login(@RequestParam("name") String name, @RequestParam("password")String password){
        return userService.login(name,password);
    }

    @ResponseBody
    @RequestMapping("/getSalary")
    public List<Salary> getSalary(@RequestParam("id")int id){
        return userService.getSalary(id);
    }

    @ResponseBody
    @RequestMapping("/updatePassword")
    public int updatePassword(@RequestParam("name")String name,@RequestParam("password") String password,@RequestParam("newPassword")String newPassword){
        return userService.updatePassword(name,password,newPassword);
    }
}
