package com.hanwul.kbscbackend.domain.careprogram;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/care-program")
@RestController
public class CareProgramController {
    private final CareProgramRepository careProgramRepository;

    @GetMapping
    public ResponseEntity<List<CareProgram>> getAllCareProgram(){
        List<CareProgram> programs = careProgramRepository.findAll();
        return ResponseEntity.ok().body(programs);
    }

    // 위치 받아서 가져오는 Controller 추가 필요
}
