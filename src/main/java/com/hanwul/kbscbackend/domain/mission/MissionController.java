package com.hanwul.kbscbackend.domain.mission;

import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccountRepository;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccountService;
import com.hanwul.kbscbackend.domain.mission.success.Success;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
@RestController
public class MissionController {

    private final MissionService missionService;


    // 미션 보여주기
    @GetMapping("")
    public BasicResponseDto<List<MissionResponseDto>> showMission(Principal principal){
        return missionService.showMission(principal);
    }

    @PutMapping("/categories/{categoryId}")
    public BasicResponseDto<Void> click(@PathVariable Long categoryId, Principal principal) {
        return missionService.toggleClick(categoryId,principal);
    }

    // 카테고리와 미션들 갖고오기
    @PutMapping("/{missionId}/success")
    public BasicResponseDto<Void> success(@PathVariable Long missionId, Principal principal){
        return missionService.successClick(missionId, principal);
    }

    @GetMapping("/success")
    public BasicResponseDto<List<Success>> successList(Principal principal){
        return missionService.successListCount(principal);
    }
}
