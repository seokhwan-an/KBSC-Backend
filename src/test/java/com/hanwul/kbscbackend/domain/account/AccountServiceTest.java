package com.hanwul.kbscbackend.domain.account;

import com.hanwul.kbscbackend.exception.WrongInputException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("회원가입 서비스")
    void registerTest() throws Exception {
        // given
        SignUpDto signUpDto = SignUpDto.builder()
                .username("username01")
                .nickname("nickname01")
                .password("1234")
                .build();

        // when
        Account savedAccount = accountService.save(signUpDto);

        // then
        assertThat(savedAccount.getUsername()).isEqualTo("username01");
        assertThat(savedAccount.getNickname()).isEqualTo("nickname01");
        System.out.println(savedAccount.getPassword());
        assertThat(passwordEncoder.matches("1234", savedAccount.getPassword())).isTrue();
    }

    @Test
    @DisplayName("로그인 테스트")
    void loginTest() throws Exception {
        // given
        Account account = Account.builder()
                .username("user01")
                .password("1234")
                .nickname("nick01")
                .build();

        LoginDto wrongUsernameLoginDto = LoginDto.builder()
                .username("wrong")
                .password("1234")
                .build();

        LoginDto wrongPasswordLoginDto = LoginDto.builder()
                .username("user01")
                .password("wrongPassword")
                .build();

        // when
        Account savedAccount = accountRepository.save(account);


        // then
        Assertions.assertThatThrownBy(() -> accountService.check(wrongUsernameLoginDto))
                .isInstanceOf(IllegalArgumentException.class);

        Assertions.assertThatThrownBy(() -> accountService.check(wrongPasswordLoginDto))
                .isInstanceOf(WrongInputException.class);
    }
}