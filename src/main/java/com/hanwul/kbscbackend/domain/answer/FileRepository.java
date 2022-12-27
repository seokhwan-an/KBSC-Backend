package com.hanwul.kbscbackend.domain.answer;

import com.hanwul.kbscbackend.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface FileRepository extends JpaRepository<File, Long> {
    List<File> findByAccountAndCreatedDateTimeBetween(Account account, LocalDateTime start, LocalDateTime end);
}
