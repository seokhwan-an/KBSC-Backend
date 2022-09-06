package com.hanwul.kbscbackend.domain.diary;

import com.hanwul.kbscbackend.common.BaseEntity;
import com.hanwul.kbscbackend.domain.account.Account;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "diary")
@Builder
@Entity
public class Diary extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    private Long likeCount;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "diary", cascade = CascadeType.ALL)
    private List<DiaryLike> diaryLikeList = new ArrayList<>();

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    private Account account;

    public void discountLike(DiaryLike diaryLike){
        this.diaryLikeList.remove(diaryLike);

    }

    public void mappingLike(DiaryLike diaryLike){
        this.diaryLikeList.add(diaryLike);
    }

    public void updateLikeCount(){
        this.likeCount = (long)this.diaryLikeList.size();
    }

    public void changeStatus(Status status){
        this.status = status;
    }

    public void changeContent(String content){
        this.content = content;
    }
}
