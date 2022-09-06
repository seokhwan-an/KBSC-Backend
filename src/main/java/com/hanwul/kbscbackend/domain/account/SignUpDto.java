package com.hanwul.kbscbackend.domain.account;


import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Getter
@NoArgsConstructor
@Data
public class SignUpDto {
    @NotBlank
    private String username;
    @NotBlank
    @Pattern(regexp = "^(?=.*[A-Za-z])((?=.*[~!@#$%^&*()_+=])|(?=.*\\d))[A-Za-z\\d~!@#$%^&*()_+=]{8,}$")
    private String password;
    @NotBlank
    private String nickname;

    @Builder
    public SignUpDto(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }
}
