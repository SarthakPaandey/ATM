package com.example.atm.domain;

import java.time.LocalDate;

public class Card {
    private final String cardNumber;
    private final String accountId;
    private final LocalDate expiryDate;

    public Card(String cardNumber, String accountId, LocalDate expiryDate) {
        this.cardNumber = cardNumber;
        this.accountId = accountId;
        this.expiryDate = expiryDate;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }
}


