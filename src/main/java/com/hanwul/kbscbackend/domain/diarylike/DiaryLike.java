package com.hanwul.kbscbackend.domain.diarylike;

import com.hanwul.kbscbackend.domain.diary.Diary;
import com.hanwul.kbscbackend.domain.user.User;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name = "DiaryLikes")
public class DiaryLike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = User.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(targetEntity = Diary.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "diary_id")
    private Diary diary;



}
