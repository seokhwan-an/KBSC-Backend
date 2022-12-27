package com.hanwul.kbscbackend.domain.emotion;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
@ActiveProfiles("test")
class EmotionServiceTest {

    @Autowired
    private EmotionService emotionService;

    @Test
    @DisplayName("감정 생성")
    void createEmotion() throws Exception {
        // given
        EmotionDto emotionDto = EmotionDto.builder()
                .content("감정1")
                .status(Status.PUBLIC)
                .build();

        // when

        // then
    }
}