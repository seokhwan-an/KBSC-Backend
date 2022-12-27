package com.hanwul.kbscbackend.exception;

import akka.http.javadsl.Http;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionCode {

    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST.value(), "회원이 이미 존재합니다."),
    WRONG_INPUT(HttpStatus.BAD_REQUEST.value(), "아이디 혹은 비밀번호가 유효하지 않습니다."),
    NOT_FOUND_USER(HttpStatus.BAD_REQUEST.value(), "유저를 찾을 수 없습니다."),
    WRONG_QUESTION_ID(HttpStatus.BAD_REQUEST.value(), "해당 Question을 찾을 수 없습니다."),
    WRONG_ANSWER_ID(HttpStatus.BAD_REQUEST.value(), "해당 Answer를 찾을 수 없습니다."),
    NOT_MY_ANSWER(HttpStatus.BAD_REQUEST.value(), "본인이 작성한 답변이 아닙니다."),
    NOT_FOUND_EMOTION(HttpStatus.BAD_REQUEST.value(), "같은 ID의 Emotion을 찾을 수 없습니다."),
    WRONG_MATCH_EMOTION(HttpStatus.BAD_REQUEST.value(), "본인이 작성한 게시글이 아닙니다."),
    WRONG_EMOTION_TYPE(HttpStatus.BAD_REQUEST.value(), "type이 PUBLIC이나 PRIVATE가 아닙니다."),
    WRONG_MATCH_MISSION(HttpStatus.BAD_REQUEST.value(), "해당 미션이 존재하지 않습니다."),
    WRONG_CATEGORY_ID(HttpStatus.BAD_REQUEST.value(), "해당 카테고리를 찾을 수 없습니다.");

    private final int code;
    private final String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
