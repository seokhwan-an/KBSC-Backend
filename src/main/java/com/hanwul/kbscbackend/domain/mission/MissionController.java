package com.hanwul.kbscbackend.domain.mission;

import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.mission.category.CategoryResponseDto;
import com.hanwul.kbscbackend.domain.mission.category.CategoryToggleDto;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccountRepository;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccountService;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/missions")
@RestController
public class MissionController {

    private final MissionService missionService;
    private final MissionRepository missionRepository;
    private final CategoryAccountService categoryAccountService;
    private final CategoryAccountRepository categoryAccountRepository;
    private final AccountRepository accountRepository;


    @GetMapping("/categories")
    public BasicResponseDto<List<CategoryResponseDto>> getCategories() {
        return missionService.getCategories();
    }

    @PutMapping("/categories/{categoryId}")
    public BasicResponseDto<CategoryToggleDto> toggleCategory(@PathVariable Long categoryId) {
        return missionService.toggleCategory(categoryId);
    }

    // 카테고리와 미션들 갖고오기
    @GetMapping
    public BasicResponseDto<List<MissionResponseDto>> getMissions() {
        return null;
//        List<MissionDto> missionDtos = categoryAccountService.missions();
//        return new BasicResponseDto<>(200, "Mission", missionDtos);
    }
//
//    @PutMapping("/{missionId}")
//    public BasicResponseDto<Long> changeStatus(@PathVariable Long missionId){
//        return service.changeStatus(missionId);
//    }

}
