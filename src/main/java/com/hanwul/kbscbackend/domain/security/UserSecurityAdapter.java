package com.hanwul.kbscbackend.domain.security;

import com.hanwul.kbscbackend.domain.account.Account;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.List;

public class UserSecurityAdapter extends User {
    private Account user;

    public UserSecurityAdapter (Account user){
        super(user.getUsername(), user.getPassword(), List.of(new SimpleGrantedAuthority("ROLE_USER")));
        this.user = user;
    }
}
