package com.hanwul.kbscbackend.domain.account;

import com.hanwul.kbscbackend.domain.security.JwtTokenProvider;
import com.hanwul.kbscbackend.exception.UserException;
import com.hanwul.kbscbackend.exception.WrongInputException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.Option;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    //회원 가입
    @Transactional
    public Account save(SignUpDto signUpDto) {
        String username = signUpDto.getUsername();
        Optional<Account> account = accountRepository.findByUsername(username);
        if(account.isPresent()){
            throw new UserException();
        }
        return accountRepository.save(toEntity(signUpDto));
    }

    // 로그인
    public void check(LoginDto loginDto) {
        // 입력받은 password
        String password = loginDto.getPassword();
        Account member = accountRepository.findByUsername(loginDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Username 입니다."));
        log.info("아이디 통과");
        if (!passwordEncoder.matches(password, member.getPassword())) {
            log.info("비번틀림");
            throw new WrongInputException();
        }
    }

    //로그아웃
    public void finish(){

    }

    public Account toEntity(SignUpDto signUpDto) {
        return Account.builder()
                .username(signUpDto.getUsername())
                .password(passwordEncoder.encode(signUpDto.getPassword()))
                .nickname(signUpDto.getNickname())
                .build();
    }

}
