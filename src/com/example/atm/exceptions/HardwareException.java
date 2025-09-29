package com.example.atm.exceptions;

public class HardwareException extends Exception {
    public HardwareException(String message) {
        super(message);
    }

    public HardwareException(String message, Throwable cause) {
        super(message, cause);
    }
}


