package com.hanwul.kbscbackend.domain.emotion;

import com.hanwul.kbscbackend.common.BaseEntity;
import com.hanwul.kbscbackend.domain.account.Account;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "emotion_like")
public class EmotionLike extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne(targetEntity = Emotion.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "emotion_id")
    private Emotion emotion;

    @Builder
    public EmotionLike(Account account, Emotion emotion) {
        this.account = account;
        this.emotion = emotion;
    }

    @Override
    public String toString() {
        return account.getUsername() + " " + emotion.getId();
    }
}
