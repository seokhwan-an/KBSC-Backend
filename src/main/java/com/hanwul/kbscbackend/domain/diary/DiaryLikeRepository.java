package com.hanwul.kbscbackend.domain.diary;

import com.hanwul.kbscbackend.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryLikeRepository extends JpaRepository<DiaryLike, Long> {

    Optional<DiaryLike> findByAccountAndDiary(Account account, Diary diary);
}
