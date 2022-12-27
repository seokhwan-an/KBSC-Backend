package com.hanwul.kbscbackend.domain.rate;

import com.hanwul.kbscbackend.domain.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
public class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account estimated;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account estimating;

    private boolean prefer;

    @Builder
    public Rate(Account estimated, Account estimating, boolean prefer) {
        this.estimated = estimated;
        this.estimating = estimating;
        this.prefer = prefer;
    }
}
