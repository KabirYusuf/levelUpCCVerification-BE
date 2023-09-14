package com.levelup.levelUpCCVerificationBE.service;

import com.levelup.levelUpCCVerificationBE.data.dto.request.CreditCardValidationRequest;
import com.levelup.levelUpCCVerificationBE.exception.LevelUpException;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
        boolean isDinersClubAndCarteBlanche = cardNumber.startsWith("36") || cardNumber.startsWith("38") || isValidCardNumberPrefixForDinersClubAndCarteBlanche(cardNumber);
        boolean isDiscover = cardNumber.startsWith("6011");
        boolean isJCB = cardNumber.startsWith("3");
        if (isAmericanExpressCard) {
            return "American Express";
        }
        else if (isMasterCard) {
            return "MasterCard";
        }
        else if (isVisaCard) {
            return "Visa";
        }
        else if (isDinersClubAndCarteBlanche) {
            return "DinersClubAndCarteBlanche";
        }
        else if (isDiscover) {
            return "Discover";
        }
        else if (isJCB) {
            return "JCB";
        }
        return "Unknown";
    }

    private boolean isValidCardNumberPrefixForDinersClubAndCarteBlanche(String cardNumber) {
        String cardNumberPrefix = cardNumber.substring(0,3);
        int conversionOfPrefixToInteger = Integer.parseInt(cardNumberPrefix);
        return (conversionOfPrefixToInteger >= 300 && conversionOfPrefixToInteger <= 305);
    }

    @Override
    public boolean isValidCreditCardNumberLength(String cardNumber) {
        String creditCardType = getCardType(cardNumber);
        switch (creditCardType){
            case "American Express": if (cardNumber.length() == 15)return true;
            case "MasterCard": if (cardNumber.length() == 16) return true;
            case "Visa": if (cardNumber.length() == 13 || cardNumber.length() == 16) return true;
            default: return false;
        }

    }

    @Override
    public boolean isValidCvv(String creditCardNumber, String cvv) {
        String creditCardType = getCardType(creditCardNumber);
        if (creditCardType.equals("American Express")) return cvv.length() == 4;
        else return cvv.length() == 3;
    }

    @Override
    public boolean isValidExpirationDate(String expirationDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/yy");
        try {
            YearMonth monthYear = YearMonth.parse(expirationDate, dateTimeFormatter);
            YearMonth currentMonthYear = YearMonth.now();
            return monthYear.isAfter(currentMonthYear);
        }catch (DateTimeParseException exception){
            throw new LevelUpException("Invalid expiry date format");
        }
    }
}
