package com.hanwul.kbscbackend.domain.answer;

import com.hanwul.kbscbackend.domain.account.Account;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AnswerDto {

    private Long id;
    private Long answer_id;
    private String question;
    private String answer;

    @Builder
    public AnswerDto(Long id, Long answer_id, String question, String answer) {
        this.id = id;
        this.answer_id = answer_id;
        this.question = question;
        this.answer = answer;
    }
}
