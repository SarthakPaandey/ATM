package com.example.atm.services;

import com.example.atm.domain.MiniStatement;
import com.example.atm.domain.Transaction;
import java.util.List;

public class SimpleMiniStatementService implements MiniStatementService {
    private final TransactionLog transactionLog;

    public SimpleMiniStatementService(TransactionLog transactionLog) {
        this.transactionLog = transactionLog;
    }

    @Override
    public MiniStatement getMiniStatement(String accountId, int limit) {
        List<Transaction> transactions = transactionLog.recent(accountId, limit);
        return new MiniStatement(accountId, transactions);
    }
}


