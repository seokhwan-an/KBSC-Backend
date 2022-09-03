package com.hanwul.kbscbackend.domain.answer;

import com.hanwul.kbscbackend.domain.question.Question;
import com.hanwul.kbscbackend.domain.account.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    Optional<Answer> findByAccount(Account account);

    Optional<Question> findByQuestion(Question question);

    List<Answer> findByCreatedDateTimeBetween(LocalDateTime start, LocalDateTime end);
}
