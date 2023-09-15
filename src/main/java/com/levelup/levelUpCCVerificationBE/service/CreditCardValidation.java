package com.levelup.levelUpCCVerificationBE.service;

import com.levelup.levelUpCCVerificationBE.data.dto.request.CreditCardValidationRequest;

public interface CreditCardValidation {
    boolean validateCreditCard(CreditCardValidationRequest creditCardValidationRequest);

    String getCardType(String cardNumber);

    boolean isValidCreditCardNumberLength(String cardNumber);

    boolean isValidCvv(String creditCardNumber, String cvv);

    boolean isValidExpirationDate(String expirationDate);

    boolean isValidCardNumberUsingLuhnAlgorithm(String creditCardNumber);

    void validCreditCardNumberFormat(String cardNumber);
}
