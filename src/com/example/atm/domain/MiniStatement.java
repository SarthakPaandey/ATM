package com.example.atm.domain;

import java.util.List;

public class MiniStatement {
    private final String accountId;
    private final List<Transaction> recentTransactions;

    public MiniStatement(String accountId, List<Transaction> recentTransactions) {
        this.accountId = accountId;
        this.recentTransactions = recentTransactions;
    }

    public String getAccountId() {
        return accountId;
    }

    public List<Transaction> getRecentTransactions() {
        return recentTransactions;
    }
}


