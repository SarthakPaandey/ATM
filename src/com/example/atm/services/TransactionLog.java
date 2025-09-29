package com.example.atm.services;

import com.example.atm.domain.Transaction;
import java.util.List;

public interface TransactionLog {
    void record(String accountId, Transaction transaction);
    List<Transaction> recent(String accountId, int limit);
}


