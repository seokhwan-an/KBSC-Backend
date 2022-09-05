package com.hanwul.kbscbackend.domain.diary;


import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Basic;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DiaryLikeRepository diaryLikeRepository;
    private final AccountRepository accountRepository;

    public BasicResponseDto<DiaryDto> read(Long diaryId) {
        Optional<Diary> result = diaryRepository.findById(diaryId);
        if (result.isEmpty()){
            throw new IllegalArgumentException("같은 id의 Diary 객체 없음");
        }

        Diary diary = result.get();
        DiaryDto diaryDto = entityToDto(diary);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "diary", diaryDto);
    }

    public BasicResponseDto<Long> create(DiaryDto diaryDto) {
        Diary diary = dtoToEntity(diaryDto);
        diaryRepository.save(diary);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "diary", diary.getId());
    }

    @Transactional
    public BasicResponseDto<Long> modify(Long diaryId, DiaryDto diaryDto) {
        Optional<Diary> byId = diaryRepository.findById(diaryId);
        if(byId.isEmpty()){
            throw new IllegalArgumentException("같은 id의 Diary 객체 없음");
        }
        Diary diary = byId.get();
        diary.changeContent(diaryDto.getContent());
        diary.changeStatus(diaryDto.getStatus());
        diaryRepository.save(diary);
        DiaryDto diaryDto1 = entityToDto(diary);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "diary", diaryDto1.getId());
    }

    public BasicResponseDto<Void> delete(Long id) {
        diaryRepository.deleteById(id);
        return new BasicResponseDto<>(HttpStatus.OK.value(), "diary", null);
    }

    public Diary dtoToEntity(DiaryDto diaryDto) {
        return Diary.builder()
                .id(diaryDto.getId())
                .content(diaryDto.getContent())
                .account(diaryDto.getAccount())
                .build();
    }

    public DiaryDto entityToDto(Diary diary) {
        return DiaryDto.builder()
                .id(diary.getId())
                .content(diary.getContent())
                .account(diary.getAccount())
                .createdDateTime(diary.getCreatedDateTime())
                .modifiedDateTime(diary.getModifiedDateTime())
                .build();
    }

    public BasicResponseDto<List<DiaryDto>> getAllDiaryDtos() {
        List<Diary> diaries = diaryRepository.findAll();
        List<DiaryDto> diaryDtos = diaries.stream().map(diary -> entityToDto(diary))
                .collect(Collectors.toList());
        return new BasicResponseDto<>(HttpStatus.OK.value(), "diary", diaryDtos);
    }

    @Transactional
    public BasicResponseDto<Void> like(Long diaryId, Principal principal){
        Account account = accountRepository.findByUsername(principal.getName()).get();
        Diary diary = diaryRepository.findById(diaryId).get();
        Optional<DiaryLike> byAccountAndDiary = diaryLikeRepository.findByAccountAndDiary(account, diary);
        byAccountAndDiary.ifPresentOrElse(
                diaryLike -> {
                    diaryLikeRepository.delete(diaryLike);
                    diary.discountLike(diaryLike);
                    diary.updateLikeCount();
                },
                () -> {
                    DiaryLike diaryLike = DiaryLike.builder().account(account).diary(diary).build();
                    diary.mappingLike(diaryLike);
                    diary.updateLikeCount();
                    diaryLikeRepository.save(diaryLike);
                }
        );
        return new BasicResponseDto<>(HttpStatus.OK.value(), "diary", null);
    }


}
