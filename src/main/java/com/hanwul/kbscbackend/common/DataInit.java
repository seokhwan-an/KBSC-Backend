package com.hanwul.kbscbackend.common;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.answer.Answer;
import com.hanwul.kbscbackend.domain.answer.AnswerRepository;
import com.hanwul.kbscbackend.domain.category.Category;
import com.hanwul.kbscbackend.domain.category.CategoryRepository;
import com.hanwul.kbscbackend.domain.diary.Diary;
import com.hanwul.kbscbackend.domain.diary.DiaryRepository;
import com.hanwul.kbscbackend.domain.diary.Status;
import com.hanwul.kbscbackend.domain.mission.Mission;
import com.hanwul.kbscbackend.domain.mission.MissionRepository;
import com.hanwul.kbscbackend.domain.question.Question;
import com.hanwul.kbscbackend.domain.question.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
//import java.util.stream.IntStream;

@Slf4j
@RequiredArgsConstructor
//@Component
public class DataInit {

    private final AccountRepository accountRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final PasswordEncoder passwordEncoder;
    private final CategoryRepository categoryRepository;
    private final MissionRepository missionRepository;
    private final DiaryRepository diaryRepository;

    @PostConstruct
    void init() {
        for (int i=0; i<5; i++) {
            Account account = Account.builder()
                    .username("user" + i)
                    .nickname("nick" + i)
                    .password(passwordEncoder.encode("1234"))
                    .build();

            account = accountRepository.save(account);

            Question question = Question.builder().content("질문" + i).build();
            questionRepository.save(question);

            for (int j = 0; j < 3; j++) {
                Answer answer = Answer.builder()
                        .answer("답변" + j)
                        .account(account)
                        .question(question)
                        .build();
                answerRepository.save(answer);
            }
        }

        Category category1 = Category.builder().content("수면장애").build();
        Category category2 = Category.builder().content("대인관계").build();
        Category category3 = Category.builder().content("트라우마").build();

        categoryRepository.save(category1);
        categoryRepository.save(category2);
        categoryRepository.save(category3);

        List<Mission> missionList = new ArrayList<>();

        missionList.add(Mission.builder().content("수면장애 미션1").category(category1).isSuccess(Boolean.FALSE).build());
        missionList.add(Mission.builder().content("수면장애 미션2").category(category1).isSuccess(Boolean.FALSE).build());
        missionList.add(Mission.builder().content("수면장애 미션3").category(category1).isSuccess(Boolean.FALSE).build());
        missionList.add(Mission.builder().content("대인관계 미션1").category(category2).isSuccess(Boolean.FALSE).build());
        missionList.add(Mission.builder().content("대인관계 미션2").category(category2).isSuccess(Boolean.FALSE).build());
        missionList.add(Mission.builder().content("대인관계 미션3").category(category2).isSuccess(Boolean.FALSE).build());
        missionList.add(Mission.builder().content("트라우마 미션1").category(category3).isSuccess(Boolean.FALSE).build());
        missionList.add(Mission.builder().content("트라우마 미션2").category(category3).isSuccess(Boolean.FALSE).build());
        missionList.add(Mission.builder().content("트라우마 미션3").category(category3).isSuccess(Boolean.FALSE).build());

        missionRepository.saveAll(missionList);

        Account account = accountRepository.findAll().stream().findFirst().get();

        List<Diary> diaryList = new ArrayList<>();
        diaryList.add(Diary.builder().content("감정1").account(account).status(Status.ACTIVE).build());
        diaryList.add(Diary.builder().content("감정2").account(account).status(Status.ACTIVE).build());
        diaryList.add(Diary.builder().content("감정3").account(account).status(Status.ACTIVE).build());
        diaryList.add(Diary.builder().content("감정4").account(account).status(Status.INACTIVE).build());
        diaryRepository.saveAll(diaryList);
    }
}
