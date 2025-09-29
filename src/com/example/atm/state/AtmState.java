package com.example.atm.state;

import com.example.atm.ATMController;

public interface AtmState {
    void insertCard(ATMController context);
    void enterPin(ATMController context);
    void withdraw(ATMController context, long amountCents);
    void miniStatement(ATMController context, int limit);
    void changePin(ATMController context);
    void ejectCard(ATMController context);
}


