package com.levelup.levelUpCCVerificationBE.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LevelUpException.class)
    public String handleLevelUpException(LevelUpException levelUpException){
        return levelUpException.getMessage();
    }
}
