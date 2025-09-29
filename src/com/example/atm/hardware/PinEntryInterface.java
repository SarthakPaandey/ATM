package com.example.atm.hardware;

import com.example.atm.domain.Card;

public interface PinEntryInterface {
    String promptForPin(Card card);
    String promptForNewPin();
    void showMessage(String message);
}


