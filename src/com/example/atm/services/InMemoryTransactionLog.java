package com.example.atm.services;

import com.example.atm.domain.Transaction;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTransactionLog implements TransactionLog {
    private final Map<String, Deque<Transaction>> logByAccount = new HashMap<>();

    @Override
    public synchronized void record(String accountId, Transaction transaction) {
        Deque<Transaction> deque = logByAccount.computeIfAbsent(accountId, k -> new ArrayDeque<>());
        deque.addFirst(transaction);
        while (deque.size() > 1000) {
            deque.removeLast();
        }
    }

    @Override
    public synchronized List<Transaction> recent(String accountId, int limit) {
        Deque<Transaction> deque = logByAccount.getOrDefault(accountId, new ArrayDeque<>());
        List<Transaction> result = new ArrayList<>();
        int i = 0;
        for (Transaction t : deque) {
            if (i++ >= limit) break;
            result.add(t);
        }
        return result;
    }
}


