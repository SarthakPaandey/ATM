package com.example.atm.services;

import com.example.atm.domain.Account;
import com.example.atm.exceptions.AuthenticationException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class SimplePinService implements PinService {
    @Override
    public void verifyPin(Account account, String pin) throws AuthenticationException {
        if (account == null) {
            throw new AuthenticationException("Account not found");
        }
        String expected = account.getPinHash();
        String actual = hashPin(pin);
        if (!MessageDigest.isEqual(expected.getBytes(StandardCharsets.UTF_8), actual.getBytes(StandardCharsets.UTF_8))) {
            throw new AuthenticationException("Invalid PIN");
        }
    }

    @Override
    public void changePin(Account account, String newPin) {
        account.setPinHash(hashPin(newPin));
    }

    @Override
    public String hashPin(String pin) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] digest = md.digest(pin.getBytes(StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 not available", e);
        }
    }
}


