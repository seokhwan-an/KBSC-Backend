package com.hanwul.kbscbackend.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    //회원 가입
    public Account save(SignUpDto signUpDto){
        return accountRepository.save(toEntity(signUpDto));
    }

    //로그인
    public Account load(LoginDto loginDto){
        Account member = accountRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 E-MAIL 입니다."));
        return member;
    }

    public Account toEntity(SignUpDto signUpDto){
        return Account.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(signUpDto.getNickname())
                .build();
    }

}
