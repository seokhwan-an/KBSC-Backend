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

    private String Content;

    private Status status;

    private LocalDateTime createdDateTime;

    private LocalDateTime modifiedDateTime;

    private List<DiaryLike> diaryLikeList = new ArrayList<>();

    private Account account;
}

