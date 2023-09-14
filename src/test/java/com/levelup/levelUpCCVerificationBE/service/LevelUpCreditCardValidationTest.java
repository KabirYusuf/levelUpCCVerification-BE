package com.levelup.levelUpCCVerificationBE.service;

import com.levelup.levelUpCCVerificationBE.data.dto.request.CreditCardValidationRequest;
import com.levelup.levelUpCCVerificationBE.exception.LevelUpException;
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
    @Test
    void testThatCardNumberLengthIsValidIfCardNumberIsFifteenAndCardTypeIsAmericanExpress(){
        String creditCardNumber = "378282246310005";
        assertEquals(15, creditCardNumber.length());
        assertEquals("American Express", creditCardValidation.getCardType(creditCardNumber));
        assertTrue(creditCardValidation.isValidCreditCardNumberLength(creditCardNumber));
    }
    @Test
    void testThatCardNumberLengthIsValidIfCardNumberIsSixteenAndCardTypeIsMasterCard(){
        String creditCardNumber = "5505105105105100";
        assertEquals(16, creditCardNumber.length());
        assertEquals("MasterCard", creditCardValidation.getCardType(creditCardNumber));
        assertTrue(creditCardValidation.isValidCreditCardNumberLength(creditCardNumber));
    }
    @Test
    void testThatCardNumberLengthIsValidIfCardNumberIsThirteenOrSixteenAndCardTypeIsVisa(){
        String creditCardNumber = "4111111111111111";
        assertEquals(16, creditCardNumber.length());
        assertEquals("Visa", creditCardValidation.getCardType(creditCardNumber));
        assertTrue(creditCardValidation.isValidCreditCardNumberLength(creditCardNumber));
    }
    @Test
    void testThatCardNumberLengthIsNotValidIfCreditCardTypeIsUnknown(){
        String creditCardNumber = "890111111111111111";
        assertEquals("Unknown", creditCardValidation.getCardType(creditCardNumber));
        assertFalse(creditCardValidation.isValidCreditCardNumberLength(creditCardNumber));
    }
    @Test
    void testThatCvvIsValidIfTheLengthIsFourAndCardTypeIsAmericanExpress(){
        String cvv = "1234";
        String creditCardNumber = "378282246310005";
        assertEquals(4, cvv.length());
        assertEquals("American Express", creditCardValidation.getCardType(creditCardNumber));
        assertTrue(creditCardValidation.isValidCvv(creditCardNumber,cvv));
    }
    @Test
    void testThatCvvIsValidIfTheLengthIsThreeAndCardTypeIsMasterCard(){
        String cvv = "123";
        String creditCardNumber = "5505105105105100";
        assertEquals(3, cvv.length());
        assertEquals("MasterCard", creditCardValidation.getCardType(creditCardNumber));
        assertTrue(creditCardValidation.isValidCvv(creditCardNumber,cvv));
    }
    @Test
    void testThatCvvIsValidIfTheLengthIsThreeAndCardTypeIsVisa(){
        String cvv = "123";
        String creditCardNumber = "4111111111111111";
        assertEquals(3, cvv.length());
        assertEquals("Visa", creditCardValidation.getCardType(creditCardNumber));
        assertTrue(creditCardValidation.isValidCvv(creditCardNumber,cvv));
    }
    @Test
    void testThatExpirationDateIsValidIfExpirationDateIsAfterCurrentDate(){
        String expirationDate = "10/25";
        assertTrue(creditCardValidation.isValidExpirationDate(expirationDate));
    }
    @Test
    void testThatExpirationDateIsNotValidIfExpirationDateIsBeforeCurrentDate(){
        String expirationDate = "10/20";
        assertFalse(creditCardValidation.isValidExpirationDate(expirationDate));
    }
    @Test
    void testThatAnExceptionIsThrownIfTheProvidedExpirationDateFormatIsIncorrect(){
        String expirationDate = "10/2034";
        assertThrows(LevelUpException.class, ()-> creditCardValidation.isValidExpirationDate(expirationDate));
    }


}