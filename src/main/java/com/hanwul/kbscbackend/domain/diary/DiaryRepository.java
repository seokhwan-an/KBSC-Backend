package com.hanwul.kbscbackend.domain.diary;

import com.hanwul.kbscbackend.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DiaryRepository extends JpaRepository<Diary, Long> {
    Optional<Diary> findByStatus(Status status);

    Optional<Diary> findByUser(User user);
}
