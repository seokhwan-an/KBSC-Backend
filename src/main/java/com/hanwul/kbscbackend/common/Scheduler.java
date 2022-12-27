package com.hanwul.kbscbackend.common;

import akka.http.javadsl.model.headers.Accept;
import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.mission.success.Success;
import com.hanwul.kbscbackend.domain.mission.success.SuccessRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@RequiredArgsConstructor
@Component
public class Scheduler {
    private final SuccessRepository successRepository;
    private final AccountRepository accountRepository;


    @Scheduled(cron = "0 0 00 * * *")
    @Transactional
    public void create(){
        List<Account> accounts = accountRepository.findAll();
        for(int i = 0; i < accounts.size(); i++){
            Success success = Success.builder()
                    .count(0L)
                    .account(accounts.get(i))
                    .build();
            successRepository.save(success);
        }
    }
}
