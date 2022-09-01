package com.hanwul.kbscbackend.domain.account;



import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Data
public class SignUpDto {
    private String username;
    private String password;
    private String nickname;

    @Builder
    public SignUpDto(String username, String password, String nickname){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public Account toEntity() {
        return Account.builder()
                .username(username)
                .password(password)
                .nickname(nickname)
                .build();
    }

}
