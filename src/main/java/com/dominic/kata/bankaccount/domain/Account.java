package com.dominic.kata.bankaccount.domain;

import java.io.PrintStream;
import java.util.Date;

/**
 * A bank account. It has a balance and a list of operations modeled by a Ledger.
 */
public class Account {

    /**
     * The account balance, 0 at creation.
     */
    private Amount balance = Amount.of(0);

    /**
     * A ledger to keep track of operation history.
     */
    private final Ledger ledger;

    public Account(Ledger ledger) {
        this.ledger = ledger;
    }

    /**
     * Credits the account with some money.
     *
     * @param value the amount to credit the account with
     * @param date  a value date for the operation
     */
    public void deposit(Amount value, Date date) {
        addOperation(value, date);
    }

    /**
     * Debits the account with some money.
     *
     * @param value the amount to debit
     * @param date  a value date for the operation
     */
    public void withdrawal(Amount value, Date date) {
        addOperation(value.changeSign(), date);
    }

    /**
     * Prints the account history, including the header.
     *
     * @param outStream a stream where to print to
     */
    public void printAccountHistory(PrintStream outStream) {
        ledger.headerToOut(outStream);
        ledger.toOut(outStream);
    }

    /**
     * Adds a new operation to the account
     *
     * @param value the amount to credit/debit the account with
     * @param date  a value date for the operation
     */
    private void addOperation(Amount value, Date date) {
        Operation operation = new Operation(value, date);
        balance = operation.applyTo(balance);
        ledger.addLedgerLine(balance, operation);
    }
}
