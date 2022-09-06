package com.hanwul.kbscbackend.domain.account;

import com.hanwul.kbscbackend.domain.security.CustomUserDetailService;
import com.hanwul.kbscbackend.domain.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@RestController
public class AccountController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AccountService accountService;

    @PostMapping("/sign-up")
    public Account join(@Valid @RequestBody SignUpDto signUpDto) {
        return accountService.save(signUpDto);
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginDto loginDto) {
        boolean authenciate = accountService.check(loginDto);
        if (authenciate) {
            return jwtTokenProvider.createToken(loginDto.getUsername(), loginDto.getRoles());
        }
        return "error";
    }
}
