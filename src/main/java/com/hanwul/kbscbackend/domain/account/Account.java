package com.hanwul.kbscbackend.domain.account;

import com.hanwul.kbscbackend.common.BaseEntity;
import com.hanwul.kbscbackend.domain.answer.Answer;
import com.hanwul.kbscbackend.domain.chatting_room.ChattingRoom;
import com.hanwul.kbscbackend.domain.diary.Diary;
import com.hanwul.kbscbackend.domain.diarylike.DiaryLike;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "account")
public class Account extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String username;

    private String password;

    @Column(length = 45)
    private String nickname;

    @OneToMany(mappedBy = "constructor", cascade = CascadeType.ALL)
    private List<ChattingRoom> chattingRoomList = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<DiaryLike> diaryLikeList = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Diary> diaryList = new ArrayList<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Answer> answerList = new ArrayList<>();

    @Builder
    public Account(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
