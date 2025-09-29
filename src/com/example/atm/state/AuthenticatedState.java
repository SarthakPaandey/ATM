package com.example.atm.state;

import com.example.atm.ATMController;
import com.example.atm.domain.MiniStatement;
import com.example.atm.domain.Transaction;

import java.time.Instant;

public class AuthenticatedState implements AtmState {
    @Override
    public void insertCard(ATMController context) {
        context.getPinEntry().showMessage("Card already inserted.");
    }

    @Override
    public void enterPin(ATMController context) {
        context.getPinEntry().showMessage("Already authenticated.");
    }

    @Override
    public void withdraw(ATMController context, long amountCents) {
        if (amountCents <= 0) {
            context.getPinEntry().showMessage("Invalid amount");
            return;
        }
        if (context.getCurrentAccount().getBalanceCents() < amountCents) {
            context.getPinEntry().showMessage("Insufficient funds");
            return;
        }
        if (!context.getCashDispenser().canDispense(amountCents)) {
            context.getPinEntry().showMessage("ATM cannot dispense this amount");
            return;
        }

        context.getCurrentAccount().setBalanceCents(context.getCurrentAccount().getBalanceCents() - amountCents);
        try {
            context.getCashDispenser().dispense(amountCents);
        } catch (Exception e) {
            context.getPinEntry().showMessage("Hardware error during dispense");
            return;
        }
        Transaction tx = new Transaction(Instant.now(), Transaction.Type.WITHDRAWAL, amountCents, "Cash withdrawal");
        context.getTransactionLog().record(context.getCurrentAccount().getAccountId(), tx);
        context.getPrinter().print(context.formatTransaction(tx));
    }

    @Override
    public void miniStatement(ATMController context, int limit) {
        MiniStatement ms = context.getMiniStatementService().getMiniStatement(context.getCurrentAccount().getAccountId(), limit);
        StringBuilder sb = new StringBuilder();
        sb.append("Mini Statement for ").append(context.getCurrentAccount().getAccountId()).append("\n");
        ms.getRecentTransactions().forEach(t -> sb.append(context.formatTransaction(t)).append("\n"));
        context.getPrinter().print(sb.toString());
    }

    @Override
    public void changePin(ATMController context) {
        String newPin = context.getPinEntry().promptForNewPin();
        if (newPin == null || newPin.isEmpty()) {
            context.getPinEntry().showMessage("PIN change cancelled");
            return;
        }
        context.getPinService().changePin(context.getCurrentAccount(), newPin);
        Transaction tx = new Transaction(Instant.now(), Transaction.Type.PIN_CHANGE, 0, "PIN changed");
        context.getTransactionLog().record(context.getCurrentAccount().getAccountId(), tx);
        context.getPrinter().print(context.formatTransaction(tx));
    }

    @Override
    public void ejectCard(ATMController context) {
        context.getCardReader().ejectCard();
        context.clearSession();
        context.setState(new IdleState());
    }
}


