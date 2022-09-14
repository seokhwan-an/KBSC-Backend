package com.hanwul.kbscbackend.domain.question;

import lombok.*;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long id;

    private String content;

}
