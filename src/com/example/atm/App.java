package com.example.atm;

import com.example.atm.domain.Account;
import com.example.atm.domain.Card;
import com.example.atm.hardware.CardReader;
import com.example.atm.hardware.CashDispenser;
import com.example.atm.hardware.PinEntryInterface;
import com.example.atm.hardware.Printer;
import com.example.atm.hardware.console.ConsoleCardReader;
import com.example.atm.hardware.console.ConsoleCashDispenser;
import com.example.atm.hardware.console.ConsolePinEntry;
import com.example.atm.hardware.console.ConsolePrinter;
import com.example.atm.services.*;

import java.time.LocalDate;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        CardReader cardReader = new ConsoleCardReader(scanner);
        PinEntryInterface pinEntry = new ConsolePinEntry(scanner);
        CashDispenser cashDispenser = new ConsoleCashDispenser(100_000); // ₹1000.00
        Printer printer = new ConsolePrinter();

        AccountRepository repo = new InMemoryAccountRepository();
        PinService pinService = new SimplePinService();
        TransactionLog txLog = new InMemoryTransactionLog();
        MiniStatementService mini = new SimpleMiniStatementService(txLog);

        // Seed an account
        Account acc = new Account("ACC-1", 50_000, pinService.hashPin("1234")); // ₹500.00
        repo.save(acc);

        ATMController atm = new ATMController(cardReader, pinEntry, cashDispenser, printer, repo, pinService, mini, txLog);

        System.out.println("Tip: For quick demo, paste: 4111111111111111,ACC-1," + LocalDate.now().plusYears(1));
        atm.insertCard();

        boolean running = true;
        while (running) {
            System.out.println("Choose action: 1) Enter PIN 2) Withdraw 3) MiniStatement 4) Change PIN 5) Eject 6) Exit");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    atm.enterPin();
                    break;
                case "2":
                    System.out.print("Amount in rupees: ");
                    String amtStr = scanner.nextLine().trim();
                    long rupees = Long.parseLong(amtStr);
                    atm.withdraw(rupees * 100);
                    break;
                case "3":
                    atm.miniStatement(10);
                    break;
                case "4":
                    atm.changePin();
                    break;
                case "5":
                    atm.ejectCard();
                    break;
                case "6":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

        System.out.println("Thank you for using the ATM.");
    }
}


