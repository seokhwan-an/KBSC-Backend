package com.hanwul.kbscbackend.domain.mission;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.mission.category.Category;
import com.hanwul.kbscbackend.domain.mission.category.CategoryRepository;
import com.hanwul.kbscbackend.domain.mission.category.CategoryResponseDto;
import com.hanwul.kbscbackend.domain.mission.category.CategoryToggleDto;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccount;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccountRepository;
import com.hanwul.kbscbackend.domain.mission.missionaccount.MissionAccountRepository;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MissionService {

    private final MissionRepository missionRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryAccountRepository categoryAccountRepository;
    private final MissionAccountRepository missionAccountRepository;
    private final AccountRepository accountRepository;

    public BasicResponseDto<List<CategoryResponseDto>> getCategories() {
        Account account = getUser();
        // 유저를 통해 선택한 카테고리들 갖고오기
        List<CategoryAccount> checkedCategoryAccount = categoryAccountRepository.findByAccount(account);
        List<Category> checkedCategories = checkedCategoryAccount.stream()
                .map(CategoryAccount::getCategory)
                .collect(Collectors.toList());
        List<Category> allCategories = categoryRepository.findAll();

        List<CategoryResponseDto> result = new ArrayList<>();
        for (Category category : allCategories) {
            CategoryResponseDto responseDto = new CategoryResponseDto(category.getId(), category.getCategory().getKorean());
            if (checkedCategories.contains(category)) {
                responseDto.setChecked(true);
            }
            result.add(responseDto);
        }
        return new BasicResponseDto<>(200, "MISSION_CATEGORY", result);
    }

    @Transactional
    public BasicResponseDto<CategoryToggleDto> toggleCategory(Long categoryId) {
        Account account = getUser();
        Optional<Category> result = categoryRepository.findById(categoryId);
        Category category = result.get();
        CategoryToggleDto toggleDto = new CategoryToggleDto();
        Optional<CategoryAccount> findCategoryAccount = categoryAccountRepository.findByAccountAndCategory(account, category);
        findCategoryAccount.ifPresentOrElse((categoryAccount -> {
            categoryAccountRepository.delete(categoryAccount);
            toggleDto.setChecked(false);
        }), () -> {
            CategoryAccount build = CategoryAccount.builder().account(account).category(category).build();
            categoryAccountRepository.save(build);
            toggleDto.setChecked(true);
        });

        return new BasicResponseDto<>(200, "MISSION_CATEGORY", toggleDto);
    }

    // create
    public Mission create(MissionResponseDto missionDto) {
        Mission mission = dtoToEntity(missionDto);
        return missionRepository.save(mission);
    }

    // read
    public Mission read(Long id) {
        Optional<Mission> mission = missionRepository.findById(id);
        if (mission.isEmpty()) {
            throw new IllegalStateException("해당 미션은 존재하지 않습니다.");
        }
        return mission.get();
    }

    // update
    @Transactional
    public Optional<Mission> update(Long id, MissionResponseDto missionDto) {
        Optional<Mission> mission = missionRepository.findById(id);
        if (mission.isEmpty()) {
            throw new IllegalStateException("해당 미션은 존재하지 않습니다.");
        }
        mission = Optional.ofNullable(dtoToEntity(missionDto));
        return mission;
    }

    //delete
    public void delete(Long id) {
        Optional<Mission> mission = missionRepository.findById(id);
        if (mission.isEmpty()) {
            throw new IllegalStateException("해당 미션은 존재하지 않습니다.");
        }
        missionRepository.delete(mission.get());
    }


    public MissionResponseDto entityToDto(Mission mission) {
        return MissionResponseDto.builder()
                .id(mission.getId())
                .content(mission.getContent())
                .isSuccess(mission.getIsSuccess())
                .build();
    }

    public Mission dtoToEntity(MissionResponseDto dto) {
        return Mission.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .isSuccess(dto.getIsSuccess())
                .build();
    }

    private Account getUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Account> findAccount = accountRepository.findByUsername(username);
        if (findAccount.isEmpty()) {
            throw new IllegalArgumentException();
        }
        return findAccount.get();
    }
}
