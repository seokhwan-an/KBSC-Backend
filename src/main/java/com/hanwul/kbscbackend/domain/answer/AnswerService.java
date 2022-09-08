package com.hanwul.kbscbackend.domain.answer;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.question.Question;
import com.hanwul.kbscbackend.domain.question.QuestionRepository;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public BasicResponseDto<Long> create(AnswerDto answerDto, Principal principal) {
        Account account = get_account(principal);
        Answer answer = dtoToEntity(answerDto, account);
        answerRepository.save(answer);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "answer", answer.getId());
    }

    public BasicResponseDto<List<AnswerDto>> findAnswerList() {
        List<Answer> result = answerRepository.findAll();
        List<AnswerDto> answerDtos = getDtoList(result);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "answer", answerDtos);
    }


    // answerId로 가져오기
    public BasicResponseDto<AnswerDto> findAnswer(Long answerId) {
        Optional<Answer> result = answerRepository.findById(answerId);
        if (result.isEmpty()) {
            throw new IllegalArgumentException("같은 ID의 Answer 없음");
        }
        Answer answer = result.get();
        AnswerDto dto = entityToDTO(answer);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "answer", dto);
    }

    public BasicResponseDto<List<AnswerDto>> findMyAnswer(Long questionId, Principal principal, String date) {
        // date : 2019-01-10
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        LocalDateTime start = localDate.atStartOfDay();
        LocalDateTime end = localDate.atTime(LocalTime.MAX);

        Optional<Question> byId = questionRepository.findById(questionId);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("같은 ID의 질문 없음");
        }
        Question question = byId.get();
        Account account = get_account(principal);
        List<Answer> result =
                answerRepository.findByQuestionAndAccountAndCreatedDateTimeBetween(question, account, start, end);
        List<AnswerDto> resultDtos = getDtoList(result);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "answer", resultDtos);
    }

    // 답변 이미 완료했는지 확인하는 서비스
    public boolean isAlreadyAnswered(Long questionId, Principal principal, String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
        LocalDateTime start = localDate.atStartOfDay();
        LocalDateTime end = localDate.atTime(LocalTime.MAX);

        Optional<Question> byId = questionRepository.findById(questionId);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("같은 ID의 질문 없음");
        }
        Question question = byId.get();
        Account account = get_account(principal);
        List<Answer> result =
                answerRepository.findByQuestionAndAccountAndCreatedDateTimeBetween(question, account, start, end);

        return (result.size() >= 1);
    }

    ///// #### 30일이면 자동으로 이렇게 처리 되도록 #####
    // 동영상 파일 가져오기


    // 동영상 파일 저장하기 -> RDS 저장하기

    // Answer 수정하기
    @Transactional
    public BasicResponseDto<Long> modify(Long answerId, AnswerDto answerDto, Principal principal) {
        Account request_account = get_account(principal);
        Optional<Answer> byId = answerRepository.findById(answerId);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("같은 ID의 객체 없음");
        }
        Answer answer = byId.get();
        if (answer.getAccount().getId() != request_account.getId()) {
            throw new IllegalArgumentException("본인이 작성한 답변이 아님");
        }
        answer.changeAnswer(answerDto.getAnswer());
        AnswerDto answerDto1 = entityToDTO(answer);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "answer", answerDto1.getId());
    }

    @Transactional
    public BasicResponseDto<Void> delete(Long answerId, Principal principal) {
        Account account = get_account(principal);
        Optional<Answer> byId = answerRepository.findById(answerId);
        if (byId.isEmpty()) {
            throw new IllegalArgumentException("같은 ID의 Answer 없음");
        }
        Answer answer = byId.get();
        if (answer.getAccount().getId() != account.getId()) {
            throw new IllegalArgumentException("본인이 작성한 답변 아님");
        }
        answerRepository.deleteById(answerId);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "answer", null);
    }


    private Account get_account(Principal principal) {
        return accountRepository.findByUsername(principal.getName()).get();
    }

    public Answer dtoToEntity(AnswerDto answerDto, Account account) {
        Optional<Question> byContent = questionRepository.findByContent(answerDto.getQuestion());
        if (byContent.isEmpty()) {
            throw new IllegalArgumentException("해당 Question 없음");
        }
        Question question = byContent.get();
        return Answer.builder()
                .id(answerDto.getId())
                .account(account)
                .question(question)
                .answer(answerDto.getAnswer())
                .build();
    }

    public AnswerDto entityToDTO(Answer answer) {
        return AnswerDto.builder()
                .id(answer.getId())
                .question(answer.getQuestion().getContent())
                .account(answer.getAccount())
                .answer(answer.getAnswer())
                .build();
    }


    private List<AnswerDto> getDtoList(List<Answer> result) {
        return result.stream().map(answer -> entityToDTO(answer))
                .collect(Collectors.toList());
    }
}
