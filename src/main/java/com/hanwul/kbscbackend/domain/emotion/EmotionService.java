package com.hanwul.kbscbackend.domain.emotion;


import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import com.hanwul.kbscbackend.dto.EmotionSearchDto;
import com.hanwul.kbscbackend.exception.WrongEmotionId;
import com.hanwul.kbscbackend.exception.WrongEmotionType;
import com.hanwul.kbscbackend.exception.WrongMatchEmotion;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class EmotionService {

    private final EmotionRepository emotionRepository;
    private final EmotionLikeRepository emotionLikeRepository;
    private final AccountRepository accountRepository;

    public BasicResponseDto<EmotionDto> read(Long emotionId) {
        Optional<Emotion> result = emotionRepository.findById(emotionId);
        if (result.isEmpty()){
            throw new WrongEmotionId();
        }
        Emotion emotion = result.get();
        EmotionDto emotionDto = entityToDto(emotion);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "emotion", emotionDto);
    }

    @Transactional
    public BasicResponseDto<Long> create(EmotionDto emotionDto, Principal principal) {
        Account account = get_account(principal);
        emotionDto.setCount(0L);
        Emotion emotion = dtoToEntity(emotionDto, account);
        emotionRepository.save(emotion);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "emotion", emotion.getId());
    }

    @Transactional
    public BasicResponseDto<Long> modify(Long emotionID, EmotionDto emotionDto, Principal principal) {
        Account request_account = get_account(principal);
        Optional<Emotion> byId = emotionRepository.findById(emotionID);
        if(byId.isEmpty()){
            throw new WrongEmotionId();
        }
        Emotion emotion = byId.get();
        if(emotion.getAccount().getId() != request_account.getId()){
            throw new WrongMatchEmotion();
        }
        emotion.changeContent(emotionDto.getContent());
        emotion.changeStatus(emotionDto.getStatus());
        EmotionDto emotionDto1 = entityToDto(emotion);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "emotion", emotionDto1.getId());
    }

    private Account get_account(Principal principal) {
        return accountRepository.findByUsername(principal.getName()).get();
    }

    @Transactional
    public BasicResponseDto<Void> delete(Long id, Principal principal) {
        Account account = get_account(principal);
        Optional<Emotion> byId = emotionRepository.findById(id);
        if(byId.isEmpty()){
            throw new WrongEmotionId();
        }
        Emotion emotion = byId.get();
        if(emotion.getAccount().getId() != account.getId()){
            throw new WrongMatchEmotion();
        }
        emotionRepository.deleteById(id);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "emotion", null);
    }

    public Emotion dtoToEntity(EmotionDto emotionDto, Account account) {
        return Emotion.builder()
                .id(emotionDto.getId())
                .status(emotionDto.getStatus())
                .account(account)
                .count(emotionDto.getCount())
                .content(emotionDto.getContent())
                .build();
    }

    public EmotionDto entityToDto(Emotion emotion) {
        return EmotionDto.builder()
                .id(emotion.getId())
                .content(emotion.getContent())
                .count(emotion.getCount())
                .status(emotion.getStatus())
                .createdDateTime(emotion.getCreatedDateTime())
                .modifiedDateTime(emotion.getModifiedDateTime())
                .build();
    }

    public BasicResponseDto<List<EmotionDto>> getAllEmotionDtos(EmotionSearchDto emotionSearchDto, Principal principal) {
        String type = emotionSearchDto.getType();
        Account account = get_account(principal);
        List<Emotion> result;
        List<EmotionLike> liked = emotionLikeRepository.findByAccount(account);

        if(type.equals("private")){
            result = emotionRepository.findAllByAccount(account);
        }
        else if(type.equals("public")){
            // public 한 애들 -> 시간 순
            result = emotionRepository.findAllPublicStatus(Sort.by("createdDateTime").descending());
       }
        else {
            throw new WrongEmotionType();
        }
        List<EmotionDto> emotionDtos = result.stream().map(emotion -> entityToDto(emotion))
                .collect(Collectors.toList());
        for (EmotionDto emotionDto : emotionDtos) {
            for (EmotionLike emotionLike : liked) {
                if(emotionLike.getEmotion().getId() == emotionDto.getId()){
                    emotionDto.setLike(true);
                }
            }
        }
        return new BasicResponseDto<>(HttpStatus.OK.value(), "emotion", emotionDtos);
    }

    // like 많이 받은 순 세 개
    @Transactional
    public BasicResponseDto<Void> like(Long emotionId, Principal principal){
        Account account = get_account(principal);
        Optional<Emotion> byId = emotionRepository.findById(emotionId);

        if(byId.isEmpty()){
            throw new WrongEmotionId();
        }

        Emotion emotion = byId.get();
        Optional<EmotionLike> byAccountAndEmotion = emotionLikeRepository.findByAccountAndEmotion(account, emotion);
        byAccountAndEmotion.ifPresentOrElse(
                emotionLike -> {
                    emotionLikeRepository.delete(emotionLike);
                    emotion.deleteLike();
                },
                () -> {
                    EmotionLike emotionLike = EmotionLike.builder().account(account).emotion(emotion).build();
                    emotionLikeRepository.save(emotionLike);
                    emotion.addLike();
                }
        );
        return new BasicResponseDto<>(HttpStatus.OK.value(), "emotion", null);
    }

    public BasicResponseDto<List<EmotionDto>> getTopLikes(Principal principal) {
        Account account = get_account(principal);
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
        List<EmotionLike> result = emotionLikeRepository.findLikeTopSevenDays(start, end);
        List<EmotionLike> liked = emotionLikeRepository.findByAccount(account);
        List<EmotionDto> list = result.stream().limit(3).map(emotionLike -> {
            Emotion emotion = emotionLike.getEmotion();
            return entityToDto(emotion);
        }).collect(Collectors.toList());

        for (EmotionDto emotionDto : list) {
            for (EmotionLike emotionLike : liked) {
                if(emotionLike.getEmotion().getId() == emotionDto.getId()){
                    emotionDto.setLike(true);
                }
            }
        }
        return new BasicResponseDto<>(HttpStatus.OK.value(), "emotion", list);
    }


}
