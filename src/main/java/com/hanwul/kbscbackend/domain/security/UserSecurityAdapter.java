package com.hanwul.kbscbackend.domain.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserSecurityAdapter extends User {
    private com.hanwul.kbscbackend.domain.user.User user;

    public UserSecurityAdapter (com.hanwul.kbscbackend.domain.user.User user){
        super(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.user = user;
    }
}
