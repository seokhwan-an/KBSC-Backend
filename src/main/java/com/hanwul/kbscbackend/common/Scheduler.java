package com.hanwul.kbscbackend.common;

import com.hanwul.kbscbackend.domain.mission.Mission;
import com.hanwul.kbscbackend.domain.mission.MissionRepository;
import com.hanwul.kbscbackend.domain.mission.MissionResponseDto;
import com.hanwul.kbscbackend.domain.mission.MissionService;
import com.hanwul.kbscbackend.domain.mission.success.Success;
import com.hanwul.kbscbackend.domain.mission.success.SuccessRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@RequiredArgsConstructor
@Component
public class Scheduler {

    private final SuccessRepository successRepository;


    @Scheduled(cron = "0 0 00 * * *")
    @Transactional
    public void create(){
        Success success = Success.builder()
                .count(0L)
                .build();
        successRepository.save(success);
    }
}
