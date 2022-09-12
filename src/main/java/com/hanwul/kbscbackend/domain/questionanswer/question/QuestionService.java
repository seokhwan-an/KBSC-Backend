package com.hanwul.kbscbackend.domain.questionanswer.question;


import com.hanwul.kbscbackend.domain.answer.Answer;
import com.hanwul.kbscbackend.domain.answer.AnswerDto;
import com.hanwul.kbscbackend.domain.questionanswer.question.QuestionRepository;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class QuestionService {

    private final QuestionRepository questionRepository;

    public BasicResponseDto<List<QuestionDto>> getAllList(){
        List<Question> all = questionRepository.findAll();
        List<QuestionDto> dtoList = getDtoList(all);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "question", dtoList);
    }


    private List<QuestionDto> getDtoList(List<Question> result) {
        return result.stream().map(question -> entityToDto(question))
                .collect(Collectors.toList());
    }

    public QuestionDto entityToDto(Question question){
        return QuestionDto.builder()
                .content(question.getContent())
                .build();
    }

    public Question dtoToEntity(QuestionDto questionDto){
        return Question.builder()
                .content(questionDto.getContent())
                .build();
    }
}
