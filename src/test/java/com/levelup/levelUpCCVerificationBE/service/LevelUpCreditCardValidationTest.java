package com.levelup.levelUpCCVerificationBE.service;

import com.levelup.levelUpCCVerificationBE.data.dto.request.CreditCardValidationRequest;
import com.levelup.levelUpCCVerificationBE.exception.LevelUpException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

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
    void testThatCreditCardTypeIsMasterCardIfCreditCardNumberStartsWith51Through55(){
        String[] creditCardNumbers = {"5105105105105100", "5205105105105100", "5305105105105100",
                "5405105105105100", "5505105105105100"};
        List<String> listOfCreditCardNumber = Arrays.asList(creditCardNumbers);
        listOfCreditCardNumber.forEach(creditCardNumber -> {
            creditCardValidationRequest.setCardNumber(creditCardNumber);
            String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
            assertEquals("MasterCard", cardType);
        });
    }
    @Test
    void testThatCreditCardTypeIsDinersClubAndCarteBlancheIfCreditCardNumberStartsWith36Or38Or300Through305(){
        String [] creditCardNumbers =  {"38569309025904","36569309025904","30069309025904", "30169309025904",
                "30269309025904", "30369309025904", "30469309025904", "30569309025904"};
        List<String> listOfCreditCardNumber = Arrays.asList(creditCardNumbers);
        listOfCreditCardNumber.forEach(creditCardNumber->{
            creditCardValidationRequest.setCardNumber(creditCardNumber);
            String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
            assertEquals("DinersClubAndCarteBlanche", cardType);
        });

    }
    @Test
    void testThatCreditCardTypeIsDiscoverIfCreditCardNumberStartsWith6011(){
        String creditCardNumber = "6011000990139424";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("Discover", cardType);
    }
    @Test
    void testThatCreditCardTypeIsJCBIfCreditCardNumberStartsWith3(){
        String creditCardNumber = "3530111333300000";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("JCB", cardType);
    }
    @Test
    void testThatCreditCardTypeIsVisaIfCreditCardNumberStartsWith4(){
        String creditCardNumber = "4111111111111111";
        creditCardValidationRequest.setCardNumber(creditCardNumber);
        String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
        assertEquals("Visa", cardType);
    }
    @Test
    void testThatCardNumberLengthIsValidIfCardNumberIsSixteenAndCardTypeIsMasterCard(){
        String creditCardNumber = "5505105105105100";
        assertEquals(16, creditCardNumber.length());
        assertEquals("MasterCard", creditCardValidation.getCardType(creditCardNumber));
        assertTrue(creditCardValidation.isValidCreditCardNumberLength(creditCardNumber));
    }
    @Test
    void testThatCardNumberLengthIsValidIfCardNumberIsFourteenAndCardTypeIsDinersClubAndCarteBlanche(){
        String creditCardNumber = "30369309025904";
        assertEquals(14, creditCardNumber.length());
        assertEquals("DinersClubAndCarteBlanche", creditCardValidation.getCardType(creditCardNumber));
        assertTrue(creditCardValidation.isValidCreditCardNumberLength(creditCardNumber));
    }
    @Test
    void testThatCardNumberLengthIsValidIfCardNumberIsSixteenAndCardTypeIsDiscover(){
        String creditCardNumber = "6011000990139424";
        assertEquals(16, creditCardNumber.length());
        assertEquals("Discover", creditCardValidation.getCardType(creditCardNumber));
        assertTrue(creditCardValidation.isValidCreditCardNumberLength(creditCardNumber));
    }
    @Test
    void testThatCardNumberLengthIsValidIfCardNumberIsSixteenAndCardTypeIsJCB(){
        String creditCardNumber = "3530111333300000";
        assertEquals(16, creditCardNumber.length());
        assertEquals("JCB", creditCardValidation.getCardType(creditCardNumber));
        assertTrue(creditCardValidation.isValidCreditCardNumberLength(creditCardNumber));
    }
    @Test
    void testThatCardNumberLengthIsValidIfCardNumberIsThirteenOrSixteenAndCardTypeIsVisa(){
        String [] creditCardNumbers =  { "4111111111111111", "4111111111111"};
        List<String> listOfCreditCardNumber = Arrays.asList(creditCardNumbers);
        listOfCreditCardNumber.forEach(creditCardNumber->{
            assertEquals("Visa", creditCardValidation.getCardType(creditCardNumber));
            assertTrue(creditCardValidation.isValidCreditCardNumberLength(creditCardNumber));
        });
    }
    @Test
    void testThatCardNumberLengthIsNotValidIfCreditCardTypeIsUnknown(){
        String creditCardNumber = "890111111111111111";
        assertEquals("Unknown", creditCardValidation.getCardType(creditCardNumber));
        assertFalse(creditCardValidation.isValidCreditCardNumberLength(creditCardNumber));
    }
    @Test
    void testThatCvvIsValidIfTheLengthIsThreeAndCardTypeIsMasterCard(){
        String cvv = "123";
        String creditCardNumber = "5505105105105100";
        assertEquals(3, cvv.length());
        assertTrue(creditCardValidation.isValidCvv(cvv));
    }
    @Test
    void testThatCvvIsValidIfTheLengthIsThreeAndCardTypeIsVisa(){
        String cvv = "123";
        String creditCardNumber = "4111111111111111";
        assertEquals(3, cvv.length());
        assertTrue(creditCardValidation.isValidCvv(cvv));
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
    @Test
    void testThatCardNumberIsValidUsingLuhnAlgorithmIfTheCardNumberIsProperlyFormulated(){
        String validCreditCardNumber = "4417123456789113";
        assertTrue(creditCardValidation.isValidCardNumberUsingLuhnAlgorithm(validCreditCardNumber));
    }
    @Test
    void testThatCardNumberIsNotValidUsingLuhnAlgorithmIfTheCardNumberIsNotProperlyFormulated(){
        String inValidCreditCardNumber = "4624748233249080";
        assertFalse(creditCardValidation.isValidCardNumberUsingLuhnAlgorithm(inValidCreditCardNumber));
    }
    @Test
    void testThatAnExceptionIsThrownIfCardNumberContainsSpace(){
        String cardNumber = "4624 7482 3324 9080";
        assertThrows(LevelUpException.class, ()-> creditCardValidation.validateNumberFormat(cardNumber));
    }
    @Test
    void testThatAnExceptionIsThrownIfCardNumberContainsNonDigit(){
        String cardNumber = "462a74g233249080";
        assertThrows(LevelUpException.class, ()-> creditCardValidation.validateNumberFormat(cardNumber));
    }
    @Test
    void testThatAnExceptionIsThrownIfCvvContainsSpace(){
        String cvv = "1 23";
        assertThrows(LevelUpException.class, ()-> creditCardValidation.validateNumberFormat(cvv));
    }
    @Test
    void testThatAnExceptionIsThrownIfCvvNonDigit(){
        String cvv = "Au3";
        assertThrows(LevelUpException.class, ()-> creditCardValidation.validateNumberFormat(cvv));
    }
    @Test
    void testThatACreditCardIsValidIfAllPropertiesOfTheCreditCardAreValid(){
        creditCardValidationRequest.setCardNumber("4417123456789113");
        creditCardValidationRequest.setCvv("123");
        creditCardValidationRequest.setExpiryDate("10/25");
        assertTrue(creditCardValidation.validateCreditCard(creditCardValidationRequest));
    }
    @Test
    void testThatACreditCardIsNotValidIfCardNumberIsNotCorrect(){
        creditCardValidationRequest.setCardNumber("4624748233249080");
        creditCardValidationRequest.setCvv("123");
        creditCardValidationRequest.setExpiryDate("10/25");
        assertFalse(creditCardValidation.validateCreditCard(creditCardValidationRequest));
    }
    @Test
    void testThatACreditCardIsNotValidIfCvvIsNotCorrect(){
        creditCardValidationRequest.setCardNumber("4417123456789113");
        creditCardValidationRequest.setCvv("123678");
        creditCardValidationRequest.setExpiryDate("10/25");
        assertFalse(creditCardValidation.validateCreditCard(creditCardValidationRequest));
    }
    @Test
    void testThatACreditCardIsNotValidIfCardHasExpired(){
        creditCardValidationRequest.setCardNumber("4417123456789113");
        creditCardValidationRequest.setCvv("123");
        creditCardValidationRequest.setExpiryDate("10/22");
        assertFalse(creditCardValidation.validateCreditCard(creditCardValidationRequest));
    }
    @Test
    void testThatAnExceptionIsThrownIfCreditCardNumberFormatIsIncorrect(){
        creditCardValidationRequest.setCardNumber("4417 12345 6789113");
        creditCardValidationRequest.setCvv("123");
        creditCardValidationRequest.setExpiryDate("10/25");
        assertThrows(LevelUpException.class, ()->creditCardValidation.validateCreditCard(creditCardValidationRequest));
    }
    @Test
    void testThatAnExceptionIsThrownIfCreditCardNumberContainNonDigit(){
        creditCardValidationRequest.setCardNumber("441n12345m6789113");
        creditCardValidationRequest.setCvv("123");
        creditCardValidationRequest.setExpiryDate("10/25");
        assertThrows(LevelUpException.class, ()->creditCardValidation.validateCreditCard(creditCardValidationRequest));
    }
    @Test
    void testThatAnExceptionIsThrownIfCvvContainNonDigit(){
        creditCardValidationRequest.setCardNumber("4417123456789113");
        creditCardValidationRequest.setCvv("12v");
        creditCardValidationRequest.setExpiryDate("10/25");
        assertThrows(LevelUpException.class, ()->creditCardValidation.validateCreditCard(creditCardValidationRequest));
    }
    @Test
    void testThatAnExceptionIsThrownIfCvvWhiteSpace(){
        creditCardValidationRequest.setCardNumber("4417123456789113");
        creditCardValidationRequest.setCvv("12 3");
        creditCardValidationRequest.setExpiryDate("10/25");
        assertThrows(LevelUpException.class, ()->creditCardValidation.validateCreditCard(creditCardValidationRequest));
    }



}