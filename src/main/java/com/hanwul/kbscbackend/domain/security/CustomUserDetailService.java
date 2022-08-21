package com.hanwul.kbscbackend.domain.security;

import com.hanwul.kbscbackend.domain.user.User;
import com.hanwul.kbscbackend.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {

       User user = userRepository.findByNickname(nickname);

        if(user == null) throw new UsernameNotFoundException(String.format("NICKNAME : [%s]을 찾을 수 없습니다", nickname));

        return new UserSecurityAdapter(user);
    }
}
