package com.hanwul.kbscbackend.domain.emotion;

import com.hanwul.kbscbackend.dto.BasicResponseDto;
import com.hanwul.kbscbackend.dto.EmotionSearchDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/emotion")
@RestController
@Slf4j
public class EmotionController {

    private final EmotionService emotionService;

    @GetMapping
    public BasicResponseDto<List<EmotionDto>> getAllPosts(EmotionSearchDto emotionSearchDto, Principal principal) {
        log.info("{}", emotionService.getAllEmotionDtos(emotionSearchDto, principal));
        return emotionService.getAllEmotionDtos(emotionSearchDto, principal);
    }

    @GetMapping("/top")
    public BasicResponseDto<List<EmotionDto>> getTop(Principal principal){
        return emotionService.getTopLikes(principal);
    }

    @PostMapping
    public BasicResponseDto<Long> createPost(@RequestBody EmotionDto emotionDto, Principal principal) {
        return emotionService.create(emotionDto, principal);
    }

    @GetMapping("/{emotionId}")
    public BasicResponseDto<EmotionDto> readPost(@PathVariable Long emotionId) {
        return emotionService.read(emotionId);
    }

    @PutMapping("/{emotionId}")
    public BasicResponseDto<Long> updatePost(@PathVariable Long emotionId, @RequestBody EmotionDto emotionDto, Principal principal){
        return emotionService.modify(emotionId, emotionDto, principal);
    }

    @DeleteMapping("/{emotionId}")
    public BasicResponseDto<Void> deletePost(@PathVariable Long emotionId, Principal principal){
        return emotionService.delete(emotionId, principal);
    }

    // 좋아요
    @GetMapping("/{emotionId}/like")
    public BasicResponseDto<Void> likePost(@PathVariable Long emotionId, Principal principal){
        return emotionService.like(emotionId, principal);
    }
}
