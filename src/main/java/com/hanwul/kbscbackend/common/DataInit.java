package com.hanwul.kbscbackend.common;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.answer.Answer;
import com.hanwul.kbscbackend.domain.answer.AnswerRepository;
import com.hanwul.kbscbackend.domain.question.Question;
import com.hanwul.kbscbackend.domain.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
//import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
@Component
public class DataInit {

    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    void init() {
        for (int i=0; i<30; i++) {
            Account account = Account.builder()
                    .username("user" + i)
                    .nickname("nick" + i)
                    .password(passwordEncoder.encode("1234"))
                    .build();

            account = accountRepository.save(account);

            Question question = Question.builder().content("질문" + i).build();
            questionRepository.save(question);

            for (int j = 0; j < 3; j++) {
                Answer answer = Answer.builder()
                        .answer("답변" + j)
                        .account(account)
                        .question(question)
                        .build();
                answerRepository.save(answer);
            }
        }
    }
}
