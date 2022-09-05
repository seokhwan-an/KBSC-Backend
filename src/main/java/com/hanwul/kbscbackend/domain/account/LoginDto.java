package com.hanwul.kbscbackend.domain.account;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor
@Data
public class LoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @Builder
    public LoginDto(String username, String password){
        this.username = username;
        this.password = password;
    }
}
