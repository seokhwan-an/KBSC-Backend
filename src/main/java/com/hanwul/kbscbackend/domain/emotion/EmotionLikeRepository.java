package com.hanwul.kbscbackend.domain.emotion;

import com.hanwul.kbscbackend.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EmotionLikeRepository extends JpaRepository<EmotionLike, Long> {

    Optional<EmotionLike> findByAccountAndEmotion(Account account, Emotion emotion);
    List<EmotionLike> findAllByCreatedDateTimeBetween(LocalDateTime start, LocalDateTime end);

    @Query("SELECT e FROM EmotionLike e where e.createdDateTime between :start and :end GROUP BY e.emotion HAVING COUNT(e) > 1 ORDER BY COUNT(e) DESC")
    List<EmotionLike> findLikeTopSevenDays(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
