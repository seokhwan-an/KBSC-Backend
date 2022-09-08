package com.hanwul.kbscbackend.domain.mission.categoryaccount;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.mission.category.Category;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CategoryAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Category category;

    private boolean isCheck;

    @Builder
    public CategoryAccount(Account account, Category category, boolean isCheck) {
        this.account = account;
        this.category = category;
        this.isCheck = isCheck;
    }
}
