package com.example.atm.hardware.console;

import com.example.atm.domain.Card;
import com.example.atm.exceptions.CardReadException;
import com.example.atm.hardware.CardReader;

import java.time.LocalDate;
import java.util.Scanner;

public class ConsoleCardReader implements CardReader {
    private final Scanner scanner;

    public ConsoleCardReader(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public Card readCard() throws CardReadException {
        System.out.print("Insert card (enter cardNumber,accountId,yyyy-mm-dd): ");
        String line = scanner.nextLine();
        String[] parts = line.split(",");
        if (parts.length != 3) throw new CardReadException("Bad input");
        return new Card(parts[0].trim(), parts[1].trim(), LocalDate.parse(parts[2].trim()));
    }

    @Override
    public void ejectCard() {
        System.out.println("Card ejected.");
    }
}


