package com.example.atm.state;

import com.example.atm.ATMController;

public class CardInsertedState implements AtmState {
    @Override
    public void insertCard(ATMController context) {
        context.getPinEntry().showMessage("Card already inserted.");
    }

    @Override
    public void enterPin(ATMController context) {
        String pin = context.getPinEntry().promptForPin(context.getCurrentCard());
        try {
            context.getPinService().verifyPin(context.getCurrentAccount(), pin);
            context.getPinEntry().showMessage("Authenticated");
            context.setState(new AuthenticatedState());
        } catch (Exception e) {
            context.getPinEntry().showMessage("Authentication failed");
            context.getCardReader().ejectCard();
            context.clearSession();
            context.setState(new IdleState());
        }
    }

    @Override
    public void withdraw(ATMController context, long amountCents) {
        context.getPinEntry().showMessage("Enter PIN first.");
    }

    @Override
    public void miniStatement(ATMController context, int limit) {
        context.getPinEntry().showMessage("Enter PIN first.");
    }

    @Override
    public void changePin(ATMController context) {
        context.getPinEntry().showMessage("Enter PIN first.");
    }

    @Override
    public void ejectCard(ATMController context) {
        context.getCardReader().ejectCard();
        context.clearSession();
        context.setState(new IdleState());
    }
}


