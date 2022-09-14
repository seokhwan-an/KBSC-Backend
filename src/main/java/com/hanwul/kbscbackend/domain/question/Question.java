package com.hanwul.kbscbackend.domain.question;

import com.hanwul.kbscbackend.common.BaseEntity;
import com.hanwul.kbscbackend.domain.answer.Answer;
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
@Table(name = "question")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

//    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
//    private List<Answer> answerList = new ArrayList<>();

    @Builder
    public Question(String content) {
        this.content = content;
    }
}
