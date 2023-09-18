package com.levelup.levelUpCCVerificationBE.controller;

import com.levelup.levelUpCCVerificationBE.data.dto.request.CreditCardValidationRequest;
import com.levelup.levelUpCCVerificationBE.data.dto.response.ApiResponse;
import com.levelup.levelUpCCVerificationBE.service.CreditCardValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/credit-card/validate/")
@Slf4j
@CrossOrigin("*")
public class CreditCardValidationController {
    @Autowired
    private CreditCardValidation creditCardValidation;
    @PostMapping
    public ResponseEntity<ApiResponse> validateCreditCard(@RequestBody CreditCardValidationRequest creditCardValidationRequest){
        boolean isValidCard = creditCardValidation.validateCreditCard(creditCardValidationRequest);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setSuccessfulRequest(true);
        apiResponse.setData("Is card valid? : " + isValidCard);
        return ResponseEntity.ok(apiResponse);
    }
}
