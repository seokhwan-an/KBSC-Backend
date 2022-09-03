package com.hanwul.kbscbackend.domain.diary;

import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/v1/diary")
@RestController
public class DiaryController {

    private final DiaryService diaryService;

    @GetMapping
    public BasicResponseDto<List<DiaryDto>> getAllPosts() {
        return diaryService.getAllDiaryDtos();
    }

    @PostMapping
    public BasicResponseDto<Long> createPost(@RequestBody DiaryDto diaryDto) {
        return diaryService.create(diaryDto);
    }

    @GetMapping("/{diaryId}")
    public BasicResponseDto<DiaryDto> readPost(@PathVariable Long diaryId) {
        return diaryService.read(diaryId);
    }

    @PutMapping("/{diaryId}")
    public BasicResponseDto<Long> updatePost(@PathVariable Long diaryId, @RequestBody DiaryDto diaryDto){
        return diaryService.modify(diaryId, diaryDto);
    }

    @DeleteMapping("/{diaryId}")
    public BasicResponseDto<Void> deletePost(@PathVariable Long diaryId){
        return diaryService.delete(diaryId);
    }

    // 좋아요
    @GetMapping("/{diaryId}/like")
    public void likePost(@PathVariable Long diaryId){
//        Optional<Diary> diary = diaryRepository.findById(diaryId);
//         diary 좋아요 로직
    }
}
