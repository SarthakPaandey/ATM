package com.example.atm;

import com.example.atm.domain.Account;
import com.example.atm.domain.Card;
import com.example.atm.domain.Transaction;
import com.example.atm.hardware.CardReader;
import com.example.atm.hardware.CashDispenser;
import com.example.atm.hardware.PinEntryInterface;
import com.example.atm.hardware.Printer;
import com.example.atm.services.AccountRepository;
import com.example.atm.services.MiniStatementService;
import com.example.atm.services.PinService;
import com.example.atm.services.TransactionLog;
import com.example.atm.state.AtmState;
import com.example.atm.state.IdleState;

public class ATMController {
    private final CardReader cardReader;
    private final PinEntryInterface pinEntry;
    private final CashDispenser cashDispenser;
    private final Printer printer;
    private final AccountRepository accounts;
    private final PinService pinService;
    private final MiniStatementService miniStatementService;
    private final TransactionLog transactionLog;

    private AtmState state;
    private Card currentCard;
    private Account currentAccount;

    public ATMController(
            CardReader cardReader,
            PinEntryInterface pinEntry,
            CashDispenser cashDispenser,
            Printer printer,
            AccountRepository accounts,
            PinService pinService,
            MiniStatementService miniStatementService,
            TransactionLog transactionLog
    ) {
        this.cardReader = cardReader;
        this.pinEntry = pinEntry;
        this.cashDispenser = cashDispenser;
        this.printer = printer;
        this.accounts = accounts;
        this.pinService = pinService;
        this.miniStatementService = miniStatementService;
        this.transactionLog = transactionLog;
        this.state = new IdleState();
    }

    // State delegations
    public void insertCard() { state.insertCard(this); }
    public void enterPin() { state.enterPin(this); }
    public void withdraw(long amountCents) { state.withdraw(this, amountCents); }
    public void miniStatement(int limit) { state.miniStatement(this, limit); }
    public void changePin() { state.changePin(this); }
    public void ejectCard() { state.ejectCard(this); }

    // Getters for dependencies (used by states)
    public CardReader getCardReader() { return cardReader; }
    public PinEntryInterface getPinEntry() { return pinEntry; }
    public CashDispenser getCashDispenser() { return cashDispenser; }
    public Printer getPrinter() { return printer; }
    public AccountRepository getAccounts() { return accounts; }
    public PinService getPinService() { return pinService; }
    public MiniStatementService getMiniStatementService() { return miniStatementService; }
    public TransactionLog getTransactionLog() { return transactionLog; }

    // Session management
    public Card getCurrentCard() { return currentCard; }
    public void setCurrentCard(Card currentCard) { this.currentCard = currentCard; }
    public Account getCurrentAccount() { return currentAccount; }
    public void setCurrentAccount(Account currentAccount) { this.currentAccount = currentAccount; }
    public void clearSession() { this.currentCard = null; this.currentAccount = null; }
    public void setState(AtmState newState) { this.state = newState; }
    public AtmState getState() { return state; }

    // Helpers
    public String formatTransaction(Transaction t) {
        return t.getTimestamp() + " | " + t.getType() + " | " + centsToRupees(t.getAmountCents()) + " | " + t.getNote();
    }

    public String centsToRupees(long cents) {
        long rupees = cents / 100;
        long paise = Math.abs(cents % 100);
        return "₹" + rupees + "." + (paise < 10 ? ("0" + paise) : paise);
    }
}


