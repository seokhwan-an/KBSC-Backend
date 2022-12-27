package com.hanwul.kbscbackend.domain.questionanswer.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findByContent(String content);
}
