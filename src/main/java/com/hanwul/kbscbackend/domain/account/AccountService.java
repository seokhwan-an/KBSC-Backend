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
    public Account save(SignUpDto signUpDto) {
        return accountRepository.save(toEntity(signUpDto));
    }

    // 로그인
    public boolean check(LoginDto loginDto) {
        // 입력받은 password
        String password = loginDto.getPassword();
        Account member = accountRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Username 입니다."));
        if (passwordEncoder.matches(password, member.getPassword())) {
            return true;
        }
        return false;
    }

    public Account toEntity(SignUpDto signUpDto) {
        return Account.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(signUpDto.getNickname())
                .build();
    }

}
