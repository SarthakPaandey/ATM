package com.example.atm.hardware;

import com.example.atm.domain.Card;
import com.example.atm.exceptions.CardReadException;

public interface CardReader {
    Card readCard() throws CardReadException;
    void ejectCard();
}


