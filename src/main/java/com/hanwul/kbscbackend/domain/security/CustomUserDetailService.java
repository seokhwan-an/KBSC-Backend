package com.hanwul.kbscbackend.domain.security;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final AccountRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String nickname) throws UsernameNotFoundException {

       Optional<Account> result = userRepository.findByUsername(nickname);

        if(result.isEmpty()) {
            throw new UsernameNotFoundException(String.format("USERNAME : [%s]을 찾을 수 없습니다", nickname));
        }

        return new UserSecurityAdapter(result.get());
    }
}
