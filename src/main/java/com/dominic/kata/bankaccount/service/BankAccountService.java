package com.dominic.kata.bankaccount.service;

import com.dominic.kata.bankaccount.domain.Account;
import com.dominic.kata.bankaccount.domain.Amount;
import com.dominic.kata.bankaccount.domain.Ledger;
import lombok.RequiredArgsConstructor;

import java.io.PrintStream;
import java.util.Date;

/**
 * This class exposes a Service-API for the Bank Account project.
 */
public class BankAccountService {

    private final Account account;

    public BankAccountService() {
        this.account = new Account(new Ledger());
    }

    public void deposit(double amount, Date date) {
        account.deposit(Amount.of(amount), date);
    }

    public void withdrawal(double amount, Date date) {
        account.withdrawal(Amount.of(amount), date);
    }

    public void printAccountHistory() {
        account.printAccountHistory(System.out);
    }

    public void printAccountHistory(PrintStream printStream) {
        account.printAccountHistory(printStream);
    }

}
