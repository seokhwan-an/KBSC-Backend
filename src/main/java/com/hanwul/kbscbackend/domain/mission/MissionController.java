package com.hanwul.kbscbackend.domain.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/mission")
@RestController
public class MissionController {

    @GetMapping
    public void getRandomMission(){
        // 카테고리에 맞게 랜덤으로 미션 하나씩 가져오는 서비스
        // return ResponseEntity.ok().body();
    }

}
