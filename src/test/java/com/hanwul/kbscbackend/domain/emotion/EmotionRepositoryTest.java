package com.hanwul.kbscbackend.domain.emotion;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class EmotionRepositoryTest {

    @Autowired
    private EmotionRepository emotionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("감정 엔티티 생성")
    void createEmotion() throws Exception {
        // given
        Account account = Account.builder()
                .username("test01")
                .nickname("nickname01")
                .password(passwordEncoder.encode("1234"))
                .build();

        accountRepository.save(account);

        Emotion emotion = Emotion.builder()
                .content("감정1")
                .account(account)
                .status(Status.PUBLIC)
                .build();
        // when
        Emotion savedEmotion = emotionRepository.save(emotion);

        // then
        assertThat(savedEmotion.getAccount()).isSameAs(account);
        assertThat(savedEmotion.getContent()).isEqualTo(emotion.getContent());
        assertThat(savedEmotion.getStatus()).isEqualTo(Status.PUBLIC);
    }

    @Test
    @DisplayName("STATUS 별 테스트")
    void findByStatusTest() throws Exception {
        // given
        Emotion emotion1 = Emotion.builder()
                .content("감정1")
                .status(Status.PUBLIC)
                .build();

        Emotion emotion2 = Emotion.builder()
                .content("감정2")
                .status(Status.PRIVATE)
                .build();

        Emotion emotion3 = Emotion.builder()
                .content("감정3")
                .status(Status.PUBLIC)
                .build();

        Emotion emotion4 = Emotion.builder()
                .content("감정4")
                .status(Status.PRIVATE)
                .build();
        // when
        emotionRepository.save(emotion1);
        emotionRepository.save(emotion2);
        emotionRepository.save(emotion3);
        emotionRepository.save(emotion4);

        // then
        assertThat(emotionRepository.findByStatus(Status.PUBLIC).size()).isEqualTo(2);
        assertThat(emotionRepository.findByStatus(Status.PRIVATE).size()).isEqualTo(2);
    }

    @Test
    @DisplayName("find by Account 테스트")
    void findByAccountTest() throws Exception {
        // given
        Account account = Account.builder()
                .username("test01")
                .nickname("nickname01")
                .password(passwordEncoder.encode("1234"))
                .build();

        accountRepository.save(account);

        Emotion emotion1 = Emotion.builder()
                .content("감정1")
                .account(account)
                .status(Status.PUBLIC)
                .build();

        Emotion emotion2 = Emotion.builder()
                .content("감정2")
                .account(account)
                .status(Status.PRIVATE)
                .build();

        // when
        emotionRepository.save(emotion1);
        emotionRepository.save(emotion2);

        // then
        List<Emotion> result = emotionRepository.findAllByAccount(account);
        long publicCount = result.stream().filter(emotion -> emotion.getStatus() == Status.PUBLIC).count();
        long privateCount = result.stream().filter(emotion -> emotion.getStatus() == Status.PRIVATE).count();
        assertThat(publicCount).isEqualTo(1);
        assertThat(privateCount).isEqualTo(1);
    }

    @Test
    @DisplayName("findAllPublic Test")
    void findByAllPublicTest() throws Exception {
        // given
        Emotion emotion1 = Emotion.builder()
                .content("감정1")
                .status(Status.PUBLIC)
                .build();

        Emotion emotion2 = Emotion.builder()
                .content("감정2")
                .status(Status.PRIVATE)
                .build();

        Emotion emotion3 = Emotion.builder()
                .content("감정3")
                .status(Status.PUBLIC)
                .build();

        Emotion emotion4 = Emotion.builder()
                .content("감정4")
                .status(Status.PRIVATE)
                .build();
        // when
        emotionRepository.save(emotion1);
        emotionRepository.save(emotion2);
        emotionRepository.save(emotion3);
        emotionRepository.save(emotion4);

        // then
        List<Emotion> result = emotionRepository.findAllPublicStatus(Sort.by("createdDateTime").descending());
        assertThat(result.size()).isEqualTo(2);
    }
}