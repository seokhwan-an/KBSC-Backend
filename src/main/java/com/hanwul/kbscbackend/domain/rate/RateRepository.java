package com.hanwul.kbscbackend.domain.rate;

import com.hanwul.kbscbackend.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RateRepository extends JpaRepository<Rate, Long> {

    int countByEstimatedAndPrefer(Account account, boolean prefer);
}
