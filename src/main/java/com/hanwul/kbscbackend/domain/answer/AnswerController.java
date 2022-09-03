package com.hanwul.kbscbackend.domain.answer;

import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/answers")
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping
    public BasicResponseDto<List<AnswerDto>> getAnswerList() {
        return answerService.findAnswerList();
    }

    @GetMapping("/{answerId}")
    public BasicResponseDto<AnswerDto> getAnswer(@PathVariable Long answerId) {
        return answerService.findAnswer(answerId);
    }


    @PostMapping
    public void post() {
    }

    @PutMapping
    public void put() {

    }

    @DeleteMapping
    public void delte() {

    }
}
