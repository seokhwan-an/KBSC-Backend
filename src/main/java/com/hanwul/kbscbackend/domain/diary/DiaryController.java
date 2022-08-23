package com.hanwul.kbscbackend.domain.diary;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    private final DiaryRepository diaryRepository;

    @GetMapping
    public ResponseEntity<List<Diary>> getAllPosts() {
        List<Diary> posts = diaryRepository.findAll();
        return ResponseEntity.ok().body(posts);
    }

    @PostMapping
    public void createPost(@RequestBody DiaryDto diaryDto){
        // DiaryDto -> Diary 로직
        // diaryRepository.save();
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<Diary> readPost(@PathVariable Long diaryId) {
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        if(!diary.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(diary.get());
    }

    @PutMapping("/{diaryId}")
    public void updatePost(@PathVariable Long diaryId, @RequestBody DiaryDto diaryDto){
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        // DiaryDto -> Diary update 로직
    }

    @DeleteMapping("/{diaryId}")
    public void deletePost(@PathVariable Long diaryId){
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        diaryRepository.delete(diary.get());
    }

    // 좋아요
    @GetMapping("/{diaryId}/like")
    public void likePost(@PathVariable Long diaryId){
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        // diary 좋아요 로직
    }
}
