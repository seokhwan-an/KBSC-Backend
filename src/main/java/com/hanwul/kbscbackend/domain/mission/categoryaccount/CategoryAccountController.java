package com.hanwul.kbscbackend.domain.mission.categoryaccount;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/mission")
@RestController
public class CategoryAccountController {

    private final CategoryAccountService categoryAccountService;

}
