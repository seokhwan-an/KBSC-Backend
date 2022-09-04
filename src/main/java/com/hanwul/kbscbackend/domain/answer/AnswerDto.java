package com.hanwul.kbscbackend.domain.answer;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDto {

    private Long id;
    private String question;
    private String answer;
}
