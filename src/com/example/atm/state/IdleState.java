package com.example.atm.state;

import com.example.atm.ATMController;
import com.example.atm.domain.Account;
import com.example.atm.domain.Card;
import com.example.atm.exceptions.CardReadException;

import java.util.Optional;

public class IdleState implements AtmState {
    @Override
    public void insertCard(ATMController context) {
        try {
            Card card = context.getCardReader().readCard();
            Optional<Account> accountOpt = context.getAccounts().findById(card.getAccountId());
            if (accountOpt.isEmpty()) {
                context.getPinEntry().showMessage("Account not found");
                context.getCardReader().ejectCard();
                context.clearSession();
                context.setState(new IdleState());
                return;
            }
            context.setCurrentCard(card);
            context.setCurrentAccount(accountOpt.get());
            context.getPinEntry().showMessage("Card accepted. Please enter PIN.");
            context.setState(new CardInsertedState());
        } catch (CardReadException e) {
            context.getPinEntry().showMessage("Card read error");
            context.getCardReader().ejectCard();
            context.clearSession();
            context.setState(new IdleState());
        }
    }

    @Override
    public void enterPin(ATMController context) {
        context.getPinEntry().showMessage("Insert card first.");
    }

    @Override
    public void withdraw(ATMController context, long amountCents) {
        context.getPinEntry().showMessage("Insert card and authenticate first.");
    }

    @Override
    public void miniStatement(ATMController context, int limit) {
        context.getPinEntry().showMessage("Insert card and authenticate first.");
    }

    @Override
    public void changePin(ATMController context) {
        context.getPinEntry().showMessage("Insert card and authenticate first.");
    }

    @Override
    public void ejectCard(ATMController context) {
        context.getPinEntry().showMessage("No card to eject.");
    }
}


