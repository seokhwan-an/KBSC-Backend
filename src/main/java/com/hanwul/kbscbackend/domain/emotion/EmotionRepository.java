package com.hanwul.kbscbackend.domain.emotion;

import com.hanwul.kbscbackend.domain.account.Account;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmotionRepository extends JpaRepository<Emotion, Long> {

    List<Emotion> findByStatus(Status status);

    List<Emotion> findAllByAccount(Account account);

    @Query("SELECT e FROM Emotion e WHERE e.status =  'PUBLIC'")
    List<Emotion> findAllPublicStatus(Sort sort);
}
