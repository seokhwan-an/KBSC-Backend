package com.hanwul.kbscbackend.domain.mission;

import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/mission")
@RestController
public class MissionController {

    private final MissionService service;

    @GetMapping
    public BasicResponseDto<List<MissionDto>> getRandomMission(@RequestParam("categories") List<String> categories){
        return service.get(categories);
    }

    @PutMapping("/{missionId}")
    public BasicResponseDto<Long> changeStatus(@PathVariable Long missionId){
        return service.changeStatus(missionId);
    }

}
