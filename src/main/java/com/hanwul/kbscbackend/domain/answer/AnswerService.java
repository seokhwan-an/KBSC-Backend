package com.hanwul.kbscbackend.domain.answer;

import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;

    public BasicResponseDto<List<AnswerDto>> findAnswerList() {

        answerRepository.findAll();
        return null;
    }

    public BasicResponseDto<AnswerDto> findAnswer(Long answerId) {
        Optional<Answer> result = answerRepository.findById(answerId);
        if (result.isEmpty()) {
            throw new IllegalArgumentException("망했다 민주이야");
        }

        Answer answer = result.get();
        AnswerDto dto = entityToDTO(answer);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "answer", dto);
    }

    /*default Answer dtoToEntity(AnswerDto dto) {
        return Answer.builder()
                .
                .build()
    }*/

    public AnswerDto entityToDTO(Answer answer) {

        return AnswerDto.builder()
                .id(answer.getId())
                .question(answer.getQuestion().getContent())
                .answer(answer.getAnswer())
                .build();
    }
}
