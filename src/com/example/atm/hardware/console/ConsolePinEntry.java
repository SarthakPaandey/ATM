package com.example.atm.hardware.console;

import com.example.atm.domain.Card;
import com.example.atm.hardware.PinEntryInterface;

import java.util.Scanner;

public class ConsolePinEntry implements PinEntryInterface {
    private final Scanner scanner;

    public ConsolePinEntry(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public String promptForPin(Card card) {
        System.out.print("Enter PIN for card " + card.getCardNumber() + ": ");
        return scanner.nextLine().trim();
    }

    @Override
    public String promptForNewPin() {
        System.out.print("Enter new PIN: ");
        return scanner.nextLine().trim();
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}


