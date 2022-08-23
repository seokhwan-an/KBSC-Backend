package com.hanwul.kbscbackend.domain.account;

import com.hanwul.kbscbackend.domain.answer.Answer;
import com.hanwul.kbscbackend.domain.chatting_room.ChattingRoom;
import com.hanwul.kbscbackend.domain.diary.Diary;
import com.hanwul.kbscbackend.domain.diarylike.DiaryLike;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 45)
    private String username;

    @Column(length = 45)
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
    private List<Answer> answerList = new ArrayList<>() ;
}
