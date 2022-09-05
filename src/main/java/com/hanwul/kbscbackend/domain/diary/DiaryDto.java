package com.hanwul.kbscbackend.domain.diary;

import com.hanwul.kbscbackend.domain.account.Account;
import lombok.*;

import java.time.LocalDateTime;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiaryDto {
    private Long id;

    private String content;

    private Status status;

    private Account account;

    private LocalDateTime createdDateTime;

    private LocalDateTime modifiedDateTime;
}

