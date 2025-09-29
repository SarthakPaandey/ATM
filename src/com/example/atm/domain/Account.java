package com.example.atm.domain;

public class Account {
    private final String accountId;
    private long balanceCents;
    private String pinHash;

    public Account(String accountId, long balanceCents, String pinHash) {
        this.accountId = accountId;
        this.balanceCents = balanceCents;
        this.pinHash = pinHash;
    }

    public String getAccountId() {
        return accountId;
    }

    public long getBalanceCents() {
        return balanceCents;
    }

    public void setBalanceCents(long balanceCents) {
        this.balanceCents = balanceCents;
    }

    public String getPinHash() {
        return pinHash;
    }

    public void setPinHash(String pinHash) {
        this.pinHash = pinHash;
    }
}


