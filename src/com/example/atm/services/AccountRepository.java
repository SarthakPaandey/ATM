package com.example.atm.services;

import com.example.atm.domain.Account;
import java.util.Optional;

public interface AccountRepository {
    Optional<Account> findById(String accountId);
    void save(Account account);
}


