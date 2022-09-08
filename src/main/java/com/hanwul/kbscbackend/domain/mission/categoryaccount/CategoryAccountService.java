package com.hanwul.kbscbackend.domain.mission.categoryaccount;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.mission.MissionDto;
import com.hanwul.kbscbackend.domain.mission.MissionService;
import com.hanwul.kbscbackend.domain.mission.category.Category;
import com.hanwul.kbscbackend.domain.mission.Mission;
import com.hanwul.kbscbackend.domain.mission.MissionRepository;
import com.hanwul.kbscbackend.domain.mission.category.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class CategoryAccountService {

    private final CategoryAccountRepository categoryAccountRepository;
    private final AccountRepository accountRepository;
    private final MissionRepository missionRepository;
    private final CategoryRepository categoryRepository;
    private final MissionService missionService;

    // spring security에서 유저 정보 가져오기
    private Account getAccount(Principal principal) {
        return accountRepository.findByUsername(principal.getName()).get();
    }

    // 랜덤 추출하기
    public List<MissionDto> missions() {
//        Account account = accountRepository.findByUsername(principal.getName()).get();
//        List<CategoryAccount> categories = categoryAccountRepository.findByAccount(account);
//        List<MissionDto> result = new ArrayList<>();
//        for (CategoryAccount category : categories) {
//            Category ca = category.getCategory();
//            List<Mission> missions = missionRepository.findByCategory(ca);
//            for (Mission mission : missions) {
//                result.add(missionService.entityToDto(mission));
//            }
//        }
        List<Mission> insomnia = missionRepository.findByCategory(categoryRepository.findById())
        List<>
    }

    //카테고리 버튼 클릭시
    public void toggle(Principal principal, Category category) {
        Account user = getAccount(principal);

        Optional<CategoryAccount> result = categoryAccountRepository.findByAccountAndCategory(user, category);

        result.ifPresentOrElse(
                categoryAccount -> {
                    categoryAccountRepository.delete(categoryAccount);
                },
                () -> {
                    CategoryAccount categoryAccount = CategoryAccount.builder()
                            .account(accountRepository.findById(user.getId()).get())
                            .category(categoryRepository.findById(category.getId()).get())
                            .isCheck(true)
                            .build();
                    categoryAccountRepository.save(categoryAccount);
                }
        );
    }

    // 질문 내보내기
//    public List<Mission> missions(Principal principal){
//        Long userId = getAccount(principal).getId();
//        List<CategoryAccount> result = categoryAccountRepository.findByAccount(userId);
//    }

    private CategoryAccount dtoToEntity(CategoryAccountDto categoryAccountDto) {
        return CategoryAccount.builder()
                .account(categoryAccountDto.getAccount())
                .category(categoryAccountDto.getCategory())
                .build();
    }

    private CategoryAccountDto entityToDto(CategoryAccount categoryAccount) {
        return CategoryAccountDto.builder()
                .id(categoryAccount.getId())
                .account(categoryAccount.getAccount())
                .category(categoryAccount.getCategory())
                .build();
    }
}