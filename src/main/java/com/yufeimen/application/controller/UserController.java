package com.yufeimen.application.controller;

import com.yufeimen.application.model.Salary;
import com.yufeimen.application.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping("/getSalary")
    @PreAuthorize("hasRole('USER')")
    public List<Salary> getSalary(@RequestParam("id")int id, HttpServletRequest request, HttpServletResponse response){
        return userService.getSalary(id);
    }

    @ResponseBody
    @RequestMapping("/updatePassword")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public int updatePassword(@RequestParam("name")String name,@RequestParam("password") String password,@RequestParam("newPassword")String newPassword){
        return userService.updatePassword(name,password,newPassword);
    }

    @ResponseBody
    @RequestMapping("/getSalaryDates")
    @PreAuthorize("hasRole('USER')")
    public List<Salary> getALLSalaryDate(@RequestParam("usercode")String usercode){
        return userService.getSalaryDates(usercode);
    }

}
