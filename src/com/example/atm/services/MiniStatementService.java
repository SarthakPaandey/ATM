package com.example.atm.services;

import com.example.atm.domain.MiniStatement;

public interface MiniStatementService {
    MiniStatement getMiniStatement(String accountId, int limit);
}


