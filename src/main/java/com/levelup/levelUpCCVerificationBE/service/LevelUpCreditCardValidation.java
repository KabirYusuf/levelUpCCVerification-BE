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
        try {
            validateNumberFormat(creditCardValidationRequest.getCardNumber());
            validateNumberFormat(creditCardValidationRequest.getCvv());

            if (!isValidCardNumberUsingLuhnAlgorithm(creditCardValidationRequest.getCardNumber())) {
                return false;
            }
            if (!isValidCreditCardNumberLength(creditCardValidationRequest.getCardNumber())) {
                return false;
            }

            if (!isValidExpirationDate(creditCardValidationRequest.getExpiryDate())) {
                return false;
            }

            if (!isValidCvv(creditCardValidationRequest.getCvv())) {
                return false;
            }

            return true;
        } catch (LevelUpException levelUpException) {
            throw new LevelUpException(levelUpException.getMessage());
        }
    }

    @Override
    public String getCardType(String cardNumber) {
        boolean isMasterCard = cardNumber.startsWith("5") && cardNumber.charAt(1) >= '1' && cardNumber.charAt(1) <= '5';
        boolean isVisaCard = cardNumber.startsWith("4");
        boolean isDinersClubAndCarteBlanche = cardNumber.startsWith("36") || cardNumber.startsWith("38") || isValidCardNumberPrefixForDinersClubAndCarteBlanche(cardNumber);
        boolean isDiscover = cardNumber.startsWith("6011");
        boolean isJCB = cardNumber.startsWith("3");

        if (isMasterCard) {
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
            case "DinersClubAndCarteBlanche": if (cardNumber.length() == 14) return true;
            case "Visa": if (cardNumber.length() == 13 || cardNumber.length() == 16) return true;
            case "Discover":
            case "MasterCard":
            case "JCB": if (cardNumber.length() == 16) return true;

            default: return false;
        }

    }

    @Override
    public boolean isValidCvv(String cvv) {
        return cvv.length() == 3;
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

    @Override
    public boolean isValidCardNumberUsingLuhnAlgorithm(String creditCardNumber) {
        int sum = 0;
        boolean alternateBetweenOddAndEvenPosition = false;
        for (int i = creditCardNumber.length() - 1; i >= 0; i--) {
            int digit = Integer.parseInt(creditCardNumber.substring(i, i + 1));
            if (alternateBetweenOddAndEvenPosition) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternateBetweenOddAndEvenPosition = !alternateBetweenOddAndEvenPosition;
        }
        return (sum % 10 == 0);
    }

    @Override
    public void validateNumberFormat(String number) throws LevelUpException{
            if (number.contains(" ")) {
                throw new LevelUpException("Card number and cvv cannot contain spaces.");
            }

            if (!number.matches("\\d+")) {
                throw new LevelUpException("Card number and cvv can only contain digits.");
            }


    }
}
