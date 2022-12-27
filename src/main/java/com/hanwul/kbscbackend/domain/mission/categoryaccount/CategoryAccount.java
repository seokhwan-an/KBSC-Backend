package com.hanwul.kbscbackend.domain.mission.categoryaccount;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanwul.kbscbackend.common.BaseEntity;
import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.mission.category.Category;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CategoryAccount extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

    @Builder
    public CategoryAccount(Account account, Category category) {
        this.account = account;
        this.category = category;
    }
}
