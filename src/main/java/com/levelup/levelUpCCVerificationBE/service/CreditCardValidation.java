package com.levelup.levelUpCCVerificationBE.service;

import com.levelup.levelUpCCVerificationBE.data.dto.request.CreditCardValidationRequest;

public interface CreditCardValidation {
    boolean validateCreditCard(CreditCardValidationRequest creditCardValidationRequest);

    String getCardType(String cardNumber);
}
