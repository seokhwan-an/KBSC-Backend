package com.hanwul.kbscbackend.domain.answer;

import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/answer")
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping("/{questionId}")
    public BasicResponseDto<Long> create(@PathVariable Long questionId, @RequestBody AnswerDto answerDto, Principal principal) {
        return answerService.create(questionId, answerDto, principal);
    }

    @GetMapping("/{answerId}")
    public BasicResponseDto<AnswerDto> findAnswer(@PathVariable Long answerId) {
        return answerService.findAnswer(answerId);
    }

    @GetMapping("/{questionId}/{date}")
    public BasicResponseDto<List<AnswerDto>> findMyAnswer(@PathVariable Long questionId, @PathVariable String date, Principal principal) {
        return answerService.findMyAnswer(questionId, principal, date);
    }

    @PutMapping("/{answerId}")
    public BasicResponseDto<Long> modify(@PathVariable Long answerId, @RequestBody AnswerDto answerDto, Principal principal) {
        return answerService.modify(answerId, answerDto, principal);
    }

    @DeleteMapping("/{answerId}")
    public BasicResponseDto<Void> delete(@PathVariable Long answerId, Principal principal) {
        return answerService.delete(answerId, principal);
    }


}
