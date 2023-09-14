package com.levelup.levelUpCCVerificationBE.data.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreditCardValidationRequest {
    private String expiryDate;
    private String cvv;
    private String cardNumber;
}
