package com.yufeimen.application.secruity;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

public class JwtAuthenticationResponse implements Serializable {
    private static final long serialVersionUID = 1250166508152483573L;

    private final String token;

    Collection<? extends GrantedAuthority> roles;

    private final int id;

    private final String username;

    public JwtAuthenticationResponse(String token, Collection<? extends GrantedAuthority> roles, int id, String username) {
        this.token = token;
        this.roles = roles;
        this.id = id;
        this.username = username;
    }

    public String getToken() {
        return this.token;
    }

    public Collection<? extends GrantedAuthority> getRoles() {
        return roles;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setRoles(Collection<? extends GrantedAuthority> roles) {
        this.roles = roles;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
