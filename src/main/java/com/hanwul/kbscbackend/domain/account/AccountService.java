package com.hanwul.kbscbackend.domain.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    //회원 가입
    public Account save(SignUpDto signUpDto){
        return accountRepository.save(signUpDto.toEntity());
    }

}
