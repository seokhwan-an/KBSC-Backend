package com.hanwul.kbscbackend.domain.mission;

import com.hanwul.kbscbackend.domain.account.Account;
import com.hanwul.kbscbackend.domain.account.AccountRepository;
import com.hanwul.kbscbackend.domain.mission.category.Category;
import com.hanwul.kbscbackend.domain.mission.category.CategoryRepository;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccount;
import com.hanwul.kbscbackend.domain.mission.categoryaccount.CategoryAccountRepository;
import com.hanwul.kbscbackend.domain.mission.success.Success;
import com.hanwul.kbscbackend.domain.mission.success.SuccessRepository;
import com.hanwul.kbscbackend.dto.BasicResponseDto;
import com.hanwul.kbscbackend.exception.WrongCategoryId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final CategoryRepository categoryRepository;
    private final CategoryAccountRepository categoryAccountRepository;
    private final AccountRepository accountRepository;
    private final SuccessRepository successRepository;
    private final List<MissionResponseDto> result = new ArrayList<>();

    // 토글버튼 눌렀을 때(categoryAccount)
    @Transactional
    public BasicResponseDto<Void> toggleClick(Long categoryId, Principal principal){
        Account account = get_account(principal);
        Optional<Category> byId = categoryRepository.findById(categoryId);
        if(byId.isEmpty()){
            throw new WrongCategoryId();
        }
        Category category = byId.get();
        Optional<CategoryAccount> categoryAccount = categoryAccountRepository.findByAccountAndCategory(account, category);
        categoryAccount.ifPresentOrElse(
                ca -> {
                    categoryAccountRepository.delete(ca);
                },
                () -> {
                    CategoryAccount ca = CategoryAccount.builder().account(account).category(category).build();
                    categoryAccountRepository.save(ca);
                }
        );
        return new BasicResponseDto<>(HttpStatus.OK.value(), "categoryclick", null);
    }

    public BasicResponseDto<List<MissionResponseDto>> showMission(Principal principal){
        Account account = get_account(principal);
        List<CategoryAccount> categoryAccounts = categoryAccountRepository.findByAccount(account);
        List<MissionResponseDto> randomMission = result;
        List<MissionResponseDto> result = randomMission.stream()
                .filter(e-> {
                    return categoryAccounts.stream().anyMatch(ca -> e.getCategory().equals(ca.getCategory().getCategory()));
                }).collect(Collectors.toList());
        return new BasicResponseDto<>(200,"showmission",result);
    }

    @Transactional
    public BasicResponseDto<Void> successClick(Long missionId, Principal principal){
        Account account = get_account(principal);
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
        List<Success> success = successRepository.findSuccessToday(start,end);
        Success today = success.stream().filter(e -> e.getAccount() == account).findFirst().get();
        log.info("유저 {}", today.getAccount().getUsername());
        for(int i = 0; i < result.size(); i++){
            if(result.get(i).getId() == missionId){
                if(result.get(i).getIsSuccess() == false){
                    result.get(i).setIsSuccess(true);
                    today.addsuccess();
                    log.info("성공 {}",today.getCount());
                } else{
                    result.get(i).setIsSuccess(false);
                    today.deletesuccess();
                    log.info("실패 {}",today.getCount());
                }
            }
        }
        return new BasicResponseDto<>(200,"mission_clear",null);
    }

    @Scheduled(cron = "0 0 22 * * *")
    @Transactional(readOnly = true)
    public void randommission(){
        long random = (int)(Math.random()*4)+1;
        for(int i = 0; i < 5; i++){
            Optional<Mission> mission = missionRepository.findById(random);
            if(!mission.isPresent()){
                throw new IllegalStateException();
            }
            MissionResponseDto missionResponseDto = entityToDto(mission.get());
            result.add(missionResponseDto);
            random = random + 5;
            log.info("미션 이름 {}", missionResponseDto.getCategory().getKorean());
        }
    }
    public BasicResponseDto<List<Success>> successList(Principal principal){
        Account account = get_account(principal);
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
        List<Success> successList = successRepository.findSuccessTopSevenDay(start,end);
        List<Success> result = successList.stream().filter(e -> e.getAccount() == account).collect(Collectors.toList());
        return new  BasicResponseDto<>(200,"SUCCESS_LIST",result);
    }

    public BasicResponseDto<List<Long>> successListCount(Principal principal){
        Account account = get_account(principal);
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0, 0, 0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
        List<Success> successList = successRepository.findSuccessTopSevenDay(start,end);
//        List<Success> result = successList.stream().filter(e -> e.getAccount() == account).collect(Collectors.toList());
        List<Long> result = successList.stream().filter(e -> e.getAccount() == account)
                .map(Success::getCount)
                .collect(Collectors.toList());
        return new  BasicResponseDto<>(200,"SUCCESS_LIST",result);
    }

    public MissionResponseDto entityToDto(Mission mission) {
        return MissionResponseDto.builder()
                .id(mission.getId())
                .content(mission.getContent())
                .category(mission.getCategory().getCategory())
                .isSuccess(false)
                .build();
    }
    public Mission dtoToEntity(MissionResponseDto dto) {
        return Mission.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .build();
    }

    private Account get_account(Principal principal) {
        return accountRepository.findByUsername(principal.getName()).get();
    }

}
