package com.hanwul.kbscbackend.domain.diary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RequestMapping("/api/v1/diary")
@RestController
public class DiaryController {

    private final DiaryRepository diaryRepository;

    @GetMapping
    public List<Diary> getAllPosts() {
        List<Diary> posts = diaryRepository.findAll();
        return posts;
    }

    @PostMapping
    public void createPost(@RequestBody DiaryDto diaryDto){
        // DiaryDto -> Diary 로직
        // diaryRepository.save();
        // redirect readPost
    }

    @GetMapping("/{diaryId}")
    public Optional<Diary> readPost(@PathVariable Long diaryId) {
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        return diary;
    }

    @PatchMapping("/{diaryId}")
    public void updatePost(@PathVariable Long diaryId, @RequestBody DiaryDto diaryDto){
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        // DiaryDto -> Diary update 로직
        // redirect readPost
    }

    @DeleteMapping("/{diaryId}")
    public void deletePost(@PathVariable Long diaryId){
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        diaryRepository.delete(diary.get());
        // redirect getAllPosts
    }

    // 좋아요
    @GetMapping("/{diaryId}/like")
    public void likePost(@PathVariable Long diaryId){
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        // diary 좋아요 로직
        // redirect readPost
    }
}
