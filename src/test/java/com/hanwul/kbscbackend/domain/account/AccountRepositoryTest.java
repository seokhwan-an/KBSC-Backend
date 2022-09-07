package com.hanwul.kbscbackend.domain.account;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("계정 추가 테스트")
    void createAccount() {
        // given
        Account account = Account.builder()
                .username("test01")
                .nickname("nickname01")
                .password(passwordEncoder.encode("1234"))
                .build();

        // when
        Account savedAccount = accountRepository.save(account);

        // then
        assertThat(savedAccount.getUsername()).isEqualTo(account.getUsername());
        assertThat(savedAccount.getNickname()).isEqualTo(account.getNickname());
        assertThat(savedAccount.getPassword()).isEqualTo(account.getPassword());
        assertThat(passwordEncoder.matches("1234", savedAccount.getPassword())).isTrue();
    }

    @Test
    @DisplayName("계정 찾기 테스트")
    void findAccount() {
        // given
        Account account1 = Account.builder()
                .username("test01")
                .nickname("nickname01")
                .password(passwordEncoder.encode("1234"))
                .build();

        Account account2 = Account.builder()
                .username("test02")
                .nickname("nickname02")
                .password(passwordEncoder.encode("1234"))
                .build();

        // when
        Account savedAccount1 = accountRepository.save(account1);
        Account savedAccount2 = accountRepository.save(account2);

        Account findAccount = accountRepository.findById(savedAccount1.getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong MemberId:<" + savedAccount1.getId() + ">"));
        Account findAccount2 = accountRepository.findById(savedAccount2.getId())
                .orElseThrow(() -> new IllegalArgumentException("Wrong MemberId:<" + savedAccount2.getId() + ">"));
        // then
        Assertions.assertThat(accountRepository.count()).isEqualTo(2);
        Assertions.assertThat(findAccount.getUsername()).isEqualTo("test01");
        Assertions.assertThat(findAccount.getNickname()).isEqualTo("nickname01");
        Assertions.assertThat(findAccount2.getUsername()).isEqualTo("test02");
        Assertions.assertThat(findAccount2.getNickname()).isEqualTo("nickname02");
    }

    @Test
    @DisplayName("계정 삭제 테스트")
    void deleteAccount() throws Exception {
        // given
        Account account = Account.builder()
                .username("test01")
                .nickname("nickname01")
                .password(passwordEncoder.encode("1234"))
                .build();

        Account savedAccount = accountRepository.save(account);

        // when
        accountRepository.delete(savedAccount);

        // then
        assertThat(accountRepository.count()).isEqualTo(0);
    }
}