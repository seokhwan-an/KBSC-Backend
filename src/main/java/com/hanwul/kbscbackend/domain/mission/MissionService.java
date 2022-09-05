package com.hanwul.kbscbackend.domain.mission;

import com.hanwul.kbscbackend.dto.BasicResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MissionService {
    private final MissionRepository repository;

    public BasicResponseDto<Long> changeStatus(Long missionId){
        Mission mission = repository.findById(missionId).get();
        mission.changeStatus();
        return new BasicResponseDto<>(HttpStatus.OK.value(), "mission", mission.getId());
    }

    public void getRandom(String category){
        //category 값이 일치하는 값 랜덤으로 하나 추출

    }

    public BasicResponseDto<List<MissionDto>> get(List<String> categories){
        // 각 카테고리 별로 랜덤으로 하나씩 뽑아서
        ArrayList<MissionDto> dtos = new ArrayList<>();
        for (String category : categories) {
            getRandom(category);
            // 나온 값 Dto로 변환하고
            // 리스트에 넣고
        }
        return new BasicResponseDto<>(HttpStatus.OK.value(), "mission", dtos);
    }

    public MissionDto entityToDto(Mission mission){
        return MissionDto.builder()
                .id(mission.getId())
                .content(mission.getContent())
                .isSuccess(mission.getIsSuccess())
                .category(mission.getCategory().getContent())
                .build();
    }

    public Mission dtoToEntity(MissionDto dto){
        return Mission.builder()
                .id(dto.getId())
                .content(dto.getContent())
                .isSuccess(dto.getIsSuccess())
                .build();
    }
}
