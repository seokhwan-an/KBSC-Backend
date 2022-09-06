package com.hanwul.kbscbackend.domain.account;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Data
public class LoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    @Builder
    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
