package com.example.atm.hardware;

import com.example.atm.exceptions.HardwareException;

public interface CashDispenser {
    boolean canDispense(long amountCents);
    void dispense(long amountCents) throws HardwareException;
    long getAvailableCashCents();
}


