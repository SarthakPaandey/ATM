package com.example.atm.services;

import com.example.atm.domain.Account;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryAccountRepository implements AccountRepository {
    private final Map<String, Account> accountById = new ConcurrentHashMap<>();

    @Override
    public Optional<Account> findById(String accountId) {
        return Optional.ofNullable(accountById.get(accountId));
    }

    @Override
    public void save(Account account) {
        accountById.put(account.getAccountId(), account);
    }
}


