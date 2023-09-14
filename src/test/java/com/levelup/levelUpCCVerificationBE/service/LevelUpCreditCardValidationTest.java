package com.levelup.levelUpCCVerificationBE.service;

import com.levelup.levelUpCCVerificationBE.data.dto.request.CreditCardValidationRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LevelUpCreditCardValidationTest {
    @Autowired
    private CreditCardValidation creditCardValidation;
    private CreditCardValidationRequest creditCardValidationRequest;
    @BeforeEach
    void setup(){
        creditCardValidationRequest = new CreditCardValidationRequest();
    }

    @Test
    void testThatCreditCardTypeIsAmericanExpressIfCreditCardNumberStartsWith37(){
        String creditCardNumber = "378282246310005";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("American Express", cardType);
    }
    @Test
    void testThatCreditCardTypeIsAmericanExpressIfCreditCardNumberStartsWith34(){
        String creditCardNumber = "348282246310005";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("American Express", cardType);
    }
    @Test
    void testThatCreditCardTypeIsMasterCardIfCreditCardNumberStartsWith51(){
        String creditCardNumber = "5105105105105100";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("MasterCard", cardType);
    }
    @Test
    void testThatCreditCardTypeIsMasterCardIfCreditCardNumberStartsWith52(){
        String creditCardNumber = "5205105105105100";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("MasterCard", cardType);
    }
    @Test
    void testThatCreditCardTypeIsMasterCardIfCreditCardNumberStartsWith53(){
        String creditCardNumber = "5305105105105100";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("MasterCard", cardType);
    }
    @Test
    void testThatCreditCardTypeIsMasterCardIfCreditCardNumberStartsWith54(){
        String creditCardNumber = "5405105105105100";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("MasterCard", cardType);
    }
    @Test
    void testThatCreditCardTypeIsMasterCardIfCreditCardNumberStartsWith55(){
        String creditCardNumber = "5505105105105100";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("MasterCard", cardType);
    }
    @Test
    void testThatCreditCardTypeIsVisaIfCreditCardNumberStartsWith4(){
        String creditCardNumber = "4111111111111111";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("Visa", cardType);
    }

}