package com.hanwul.kbscbackend.domain.questionanswer.question;

import com.hanwul.kbscbackend.domain.questionanswer.question.QuestionService;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/question")
@RestController
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public BasicResponseDto<List<QuestionDto>> getAll(){
        return questionService.getAllList();
    }
}
