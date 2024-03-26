package com.saidul.Splitwise.service;

import java.util.Currency;

public abstract class CurrencyConverter {
    public static double convertToINR(Currency currency, double amount){
        double convertedAmount;
        switch (currency.getCurrencyCode()){
            case "USD" :
                convertedAmount = amount*80;
                break;
            case "EUR" :
                convertedAmount = amount*88;
                break;
            case "GBP" :
                convertedAmount = amount*98;
                break;
            case "AUD" :
                convertedAmount = amount*60;
                break;
            default:
                throw new IllegalArgumentException("Conversion to " + currency.getCurrencyCode() + " is not supported.");
        }
        return convertedAmount;
    }
}
