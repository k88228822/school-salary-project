package com.yufeimen.application.controller;

import com.yufeimen.application.model.User;
import com.yufeimen.application.secruity.JwtAuthenticationResponse;
import com.yufeimen.application.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthService authService;

    @ResponseBody
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public JwtAuthenticationResponse createAuthenticationToken(@RequestParam("username") String username,@RequestParam("password")String password) throws AuthenticationException {
        return authService.login(username,password);
    }

    @ResponseBody
    @RequestMapping(value = "${jwt.route.authentication.refresh}", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) throws AuthenticationException {
        String token = request.getHeader(tokenHeader);
        JwtAuthenticationResponse jwtResponse = authService.refresh(token);
        if(jwtResponse== null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            return ResponseEntity.ok(jwtResponse);
        }
    }

    @ResponseBody
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public User register(@ModelAttribute User addedUser) throws AuthenticationException {

        return authService.register(addedUser);
    }

}
