package com.example.atm.hardware.console;

import com.example.atm.exceptions.HardwareException;
import com.example.atm.hardware.CashDispenser;

public class ConsoleCashDispenser implements CashDispenser {
    private long availableCents;

    public ConsoleCashDispenser(long initialCents) {
        this.availableCents = initialCents;
    }

    @Override
    public boolean canDispense(long amountCents) {
        return amountCents > 0 && amountCents <= availableCents;
    }

    @Override
    public void dispense(long amountCents) throws HardwareException {
        if (!canDispense(amountCents)) {
            throw new HardwareException("Insufficient cash in dispenser");
        }
        availableCents -= amountCents;
        System.out.println("Dispensing cash: " + amountCents + " cents");
    }

    @Override
    public long getAvailableCashCents() {
        return availableCents;
    }
}


