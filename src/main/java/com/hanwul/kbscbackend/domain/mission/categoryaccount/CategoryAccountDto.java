package com.hanwul.kbscbackend.domain.mission.categoryaccount;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.mission.category.Category;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoryAccountDto {

    private Long id;

    private Account account;

    private Category category;

    private boolean isCheck;
}
