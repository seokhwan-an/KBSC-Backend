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
    public ResponseEntity<?> createPost(@RequestBody DiaryDto diaryDto){
        // DiaryDto -> Diary 로직
        // diaryRepository.save();
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/v1/diary"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @GetMapping("/{diaryId}")
    public ResponseEntity<Diary> readPost(@PathVariable Long diaryId) {
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        if(!diary.isPresent())
            return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(diary.get());
    }

    @PatchMapping("/{diaryId}")
    public ResponseEntity<?> updatePost(@PathVariable Long diaryId, @RequestBody DiaryDto diaryDto){
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        // DiaryDto -> Diary update 로직
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/v1/diary/"+diaryId));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    @DeleteMapping("/{diaryId}")
    public ResponseEntity<?> deletePost(@PathVariable Long diaryId){
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        diaryRepository.delete(diary.get());
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/v1/diary"));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }

    // 좋아요
    @GetMapping("/{diaryId}/like")
    public ResponseEntity<?> likePost(@PathVariable Long diaryId){
        Optional<Diary> diary = diaryRepository.findById(diaryId);
        // diary 좋아요 로직
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/api/v1/diary/"+diaryId));
        return new ResponseEntity<>(headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
