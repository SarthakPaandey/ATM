package com.example.atm.domain;

import java.time.Instant;

public class Transaction {
    public enum Type { WITHDRAWAL, PIN_CHANGE }

    private final Instant timestamp;
    private final Type type;
    private final long amountCents;
    private final String note;

    public Transaction(Instant timestamp, Type type, long amountCents, String note) {
        this.timestamp = timestamp;
        this.type = type;
        this.amountCents = amountCents;
        this.note = note;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Type getType() {
        return type;
    }

    public long getAmountCents() {
        return amountCents;
    }

    public String getNote() {
        return note;
    }
}


