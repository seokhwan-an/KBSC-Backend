package com.hanwul.kbscbackend.domain.diary;

import com.hanwul.kbscbackend.domain.diarylike.DiaryLike;
import com.hanwul.kbscbackend.domain.account.Account;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
}

