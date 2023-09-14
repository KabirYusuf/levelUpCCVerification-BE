package com.levelup.levelUpCCVerificationBE.service;

import com.levelup.levelUpCCVerificationBE.data.dto.request.CreditCardValidationRequest;
import org.springframework.stereotype.Service;

@Service
public class LevelUpCreditCardValidation implements CreditCardValidation{
    @Override
    public boolean validateCreditCard(CreditCardValidationRequest creditCardValidationRequest) {
        return false;
    }

    @Override
    public String getCardType(String cardNumber) {
        boolean isAmericanExpressCard = cardNumber.startsWith("37") || cardNumber.startsWith("34");
        boolean isMasterCard = cardNumber.startsWith("5") && cardNumber.charAt(1) >= '1' && cardNumber.charAt(1) <= '5';
        boolean isVisaCard = cardNumber.startsWith("4");
        if (isAmericanExpressCard) {
            return "American Express";
        }
        else if (isMasterCard) {
            return "MasterCard";
        }
        else if (isVisaCard) {
            return "Visa";
        }
        return "Unknown";
    }
}
