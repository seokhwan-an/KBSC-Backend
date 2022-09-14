package com.hanwul.kbscbackend.exception.handler.advice;

import com.hanwul.kbscbackend.exception.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserException.class)
    public ErrorResult joinUserExHandler(UserException e) {
        return new ErrorResult(ExceptionCode.ALREADY_EXIST_USER);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongInputException.class)
    public ErrorResult wrongInputExHandler(WrongInputException e) {
        return new ErrorResult(ExceptionCode.WRONG_INPUT);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult notFoundExHandler(IllegalArgumentException e) {
        return new ErrorResult(ExceptionCode.NOT_FOUND_USER);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongQuestionId.class)
    public ErrorResult wrongQuestionId(IllegalArgumentException e){
        return new ErrorResult(ExceptionCode.WRONG_QUESTION_ID);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongAnswerId.class)
    public ErrorResult wrongAnswerId(IllegalArgumentException e){
        return new ErrorResult(ExceptionCode.WRONG_ANSWER_ID);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotMyAnswer.class)
    public ErrorResult notMyAnswer(IllegalArgumentException e) {
        return new ErrorResult(ExceptionCode.NOT_MY_ANSWER);
    }
    @ExceptionHandler(WrongEmotionId.class)
    public ErrorResult wrongEmotionId(IllegalArgumentException e){
        return new ErrorResult(ExceptionCode.NOT_FOUND_EMOTION);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongMatchEmotion.class)
    public ErrorResult wrongMatchEmotion(IllegalArgumentException e){
        return new ErrorResult(ExceptionCode.WRONG_MATCH_EMOTION);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongEmotionType.class)
    public ErrorResult wrongEmotionType(IllegalArgumentException e){
        return new ErrorResult(ExceptionCode.WRONG_EMOTION_TYPE);

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongMatchMission.class)
    public ErrorResult wrongMatchMission(IllegalArgumentException e){
        return new  ErrorResult(ExceptionCode.WRONG_MATCH_MISSION);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(WrongCategoryId.class)
    public ErrorResult wrongCategoryId(IllegalArgumentException e){
        return new ErrorResult(ExceptionCode.WRONG_CATEGORY_ID);
    }
}
