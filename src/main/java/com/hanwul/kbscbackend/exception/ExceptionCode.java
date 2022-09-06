package com.hanwul.kbscbackend.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {

    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST.value(), "회원이 이미 존재합니다."),
    WRONG_INPUT(HttpStatus.BAD_REQUEST.value(), "아이디 혹은 비밀번호가 유효하지 않습니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST.value(), "유저를 찾을 수 없습니다.");

    private final int code;
    private final String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
