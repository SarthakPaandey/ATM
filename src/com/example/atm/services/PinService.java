package com.example.atm.services;

import com.example.atm.domain.Account;
import com.example.atm.exceptions.AuthenticationException;

public interface PinService {
    void verifyPin(Account account, String pin) throws AuthenticationException;
    void changePin(Account account, String newPin);
    String hashPin(String pin);
}


