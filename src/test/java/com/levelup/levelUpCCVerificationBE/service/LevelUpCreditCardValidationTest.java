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
    void testThatCreditCardTypeIsAmericanExpressIfCreditCardNumberStartsWith34Or37() {
        String[] creditCardNumbers = {"378282246310005", "348282246310005"};
        List<String> listOfCreditCardNumber = Arrays.asList(creditCardNumbers);
        listOfCreditCardNumber.forEach(creditCardNumber -> {
            creditCardValidationRequest.setCardNumber(creditCardNumber);
            String cardType = creditCardValidation.getCardType(creditCardValidationRequest.getCardNumber());
            assertEquals("American Express", cardType);
        });
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