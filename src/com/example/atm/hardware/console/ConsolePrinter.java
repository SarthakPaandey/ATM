package com.example.atm.hardware.console;

import com.example.atm.hardware.Printer;

public class ConsolePrinter implements Printer {
    @Override
    public void print(String content) {
        System.out.println("--- Receipt ---\n" + content + "\n---------------");
    }
}


