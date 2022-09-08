package com.hanwul.kbscbackend.domain.mission;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class MissionService {

    private final MissionRepository repository;

    // create
    public Mission create(MissionDto missionDto){
        Mission mission = dtoToEntity(missionDto);
        return repository.save(mission);
    }

    // read
    public Mission read(Long id){
        Optional<Mission> mission = repository.findById(id);
        if(!mission.isPresent()){
            throw new IllegalStateException("해당 미션은 존재하지 않습니다.");
        }
        return  mission.get();
    }

    // update
    @Transactional
    public Optional<Mission> update(Long id, MissionDto missionDto){
        Optional<Mission> mission = repository.findById(id);
        if(!mission.isPresent()){
            throw new IllegalStateException("해당 미션은 존재하지 않습니다.");
        }
        mission = Optional.ofNullable(dtoToEntity(missionDto));
        return mission;
    }

    //delete
    public void delete(Long id){
        Optional<Mission> mission = repository.findById(id);
        if(!mission.isPresent()){
            throw new IllegalStateException("해당 미션은 존재하지 않습니다.");
        }
        repository.delete(mission.get());
    }


    public MissionDto entityToDto(Mission mission){
        return MissionDto.builder()
                .id(mission.getId())
                .content(mission.getContent())
                .isSuccess(mission.getIsSuccess())
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
