package com.levelup.levelUpCCVerificationBE.exception;

import com.levelup.levelUpCCVerificationBE.data.dto.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LevelUpException.class)
    public ResponseEntity<ApiResponse> handleLevelUpException(LevelUpException levelUpException){
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setData(levelUpException.getMessage());
        apiResponse.setSuccessfulRequest(false);
        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }
}
