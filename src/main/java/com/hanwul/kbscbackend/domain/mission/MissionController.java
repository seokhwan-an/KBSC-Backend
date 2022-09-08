package com.hanwul.kbscbackend.domain.mission;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.mission.category.Category;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccount;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccountRepository;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccountService;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
@RestController
public class MissionController {

    private final MissionRepository missionRepository;
    private final MissionService missionService;
    private final CategoryAccountService categoryAccountService;
    private final CategoryAccountRepository categoryAccountRepository;
    private final AccountRepository accountRepository;

    @GetMapping
    public BasicResponseDto<List<MissionDto>> getRandomMission() {
        List<MissionDto> missionDtos = categoryAccountService.missions();
        return new BasicResponseDto<>(200, "Mission", missionDtos);
    }
//
//    @PutMapping("/{missionId}")
//    public BasicResponseDto<Long> changeStatus(@PathVariable Long missionId){
//        return service.changeStatus(missionId);
//    }

}
