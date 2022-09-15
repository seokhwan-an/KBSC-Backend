package com.hanwul.kbscbackend.domain.account;

import com.hanwul.kbscbackend.domain.mission.Mission;
import com.hanwul.kbscbackend.domain.mission.MissionRepository;
import com.hanwul.kbscbackend.domain.mission.missionaccount.MissionAccount;
import com.hanwul.kbscbackend.domain.mission.missionaccount.MissionAccountRepository;
import com.hanwul.kbscbackend.domain.mission.success.Success;
import com.hanwul.kbscbackend.domain.mission.success.SuccessRepository;
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
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final MissionAccountRepository missionAccountRepository;
    private final MissionRepository missionRepository;
    private final SuccessRepository successRepository;

    //회원 가입
    @Transactional
    public Account save(SignUpDto signUpDto) {
        String username = signUpDto.getUsername();
        Optional<Account> result = accountRepository.findByUsername(username);
        if(result.isPresent()){
            throw new UserException();
        }
        Account account = accountRepository.save(toEntity(signUpDto));
        List<Mission> missions = missionRepository.findAll();
        missions.forEach(mission -> {
            MissionAccount build = MissionAccount.builder()
                    .account(account)
                    .mission(mission)
                    .build();
            missionAccountRepository.save(build);
        });
        Success success = Success.builder()
                .account(account)
                .count(0L)
                .build();
        successRepository.save(success);
        log.info("{}", signUpDto);
        return account;
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
