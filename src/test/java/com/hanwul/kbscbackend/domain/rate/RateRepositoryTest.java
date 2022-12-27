package com.hanwul.kbscbackend.domain.rate;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class RateRepositoryTest {

    @Autowired
    private RateRepository rateRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("평가 테스트")
    void createRateTest() throws Exception {
        // given
        Account account1 = Account.builder().username("user01").nickname("nick01").password(passwordEncoder.encode("1234")).build();
        Account account2 = Account.builder().username("user02").nickname("nick02").password(passwordEncoder.encode("1234")).build();
        Account account3 = Account.builder().username("user03").nickname("nick03").password(passwordEncoder.encode("1234")).build();

        accountRepository.save(account1);
        accountRepository.save(account2);
        accountRepository.save(account3);

        Rate rate1 = Rate.builder().estimated(account1).estimating(account2).prefer(true).build();
        Rate rate2 = Rate.builder().estimated(account1).estimating(account3).prefer(true).build();

        // when
        rateRepository.save(rate1);
        rateRepository.save(rate2);

        // then
        Integer result = rateRepository.countByEstimatedAndPrefer(account1, true);
        Assertions.assertThat(result).isEqualTo(2);
    }
}