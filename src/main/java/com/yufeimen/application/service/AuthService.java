package com.yufeimen.application.service;

import com.yufeimen.application.mapper.UserMapper;
import com.yufeimen.application.model.User;
import com.yufeimen.application.secruity.JwtAuthenticationResponse;
import com.yufeimen.application.secruity.JwtTokenUtil;
import com.yufeimen.application.secruity.JwtUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private BaseService baseService;
    @Autowired
    private UserMapper userMapper;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthService(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userMapper = userMapper;
    }

    public User register(User userToAdd) {
        final String username = userToAdd.getUsername();
        if(userMapper.selectByName(username).size()>0) {
            return null;
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = userToAdd.getPassword();
        userToAdd.setPassword(encoder.encode(rawPassword));
        userToAdd.setRole("ROLE_USER");
        int id=userMapper.insert(userToAdd);
        userToAdd.setId(id);
        return userToAdd;
    }

    public JwtAuthenticationResponse login(String username, String password) {
        baseService.checkNameAndPassword(username,password);
        // Reload password post-security so we can generate token
        JwtUser user  = (JwtUser) userDetailsService.loadUserByUsername(username);
        final String token = jwtTokenUtil.generateToken(user);
        JwtAuthenticationResponse response=new JwtAuthenticationResponse(token,user.getAuthorities(),user.getId(),user.getUsername());
        return response;
    }

    public JwtAuthenticationResponse refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);
        if (jwtTokenUtil.canTokenBeRefreshed(token)){
            return new JwtAuthenticationResponse(jwtTokenUtil.refreshToken(token),user.getAuthorities(),user.getId(),user.getUsername());
        }
        return null;
    }
}
