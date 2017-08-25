package com.yufeimen.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    AuthenticationManager authenticationManager;

    public void checkNameAndPassword(String username, String password) {
        try {
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
            // Perform the security
            final Authentication authentication = authenticationManager.authenticate(upToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }catch (Exception e){
            throw new RuntimeException("用户名或密码错误");
        }
    }

}
