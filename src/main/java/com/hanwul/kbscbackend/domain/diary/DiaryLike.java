package com.hanwul.kbscbackend.domain.diary;

import com.hanwul.kbscbackend.domain.diary.Diary;
import com.hanwul.kbscbackend.domain.account.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "diary_like")
public class DiaryLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(targetEntity = Diary.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "diary_id")
    private Diary diary;

    @Builder
    public DiaryLike(Account account, Diary diary) {
        this.account = account;
        this.diary = diary;
    }
}
