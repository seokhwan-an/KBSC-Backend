package com.hanwul.kbscbackend.exception.handler.advice;

import com.hanwul.kbscbackend.exception.ExceptionCode;
import lombok.Data;

@Data
public class ErrorResult {
    private int code;
    private String message;

    public ErrorResult(ExceptionCode exception) {
        code = exception.getCode();
        message = exception.getMessage();
    }
}
