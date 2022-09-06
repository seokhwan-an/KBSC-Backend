package com.hanwul.kbscbackend.domain.emotion;

import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmotionDto {
    private Long id;

    private String content;

    private Status status;

    private Long count;

    private LocalDateTime createdDateTime;

    private LocalDateTime modifiedDateTime;
}

