package com.dominic.kata.bankaccount.domain;

import lombok.RequiredArgsConstructor;

import java.io.PrintStream;

/**
 * A LedgerLine is composed of an Operation and a current balance.
 */
@RequiredArgsConstructor
public class LedgerLine {

    /**
     * The current balance of the ledger's line
     */
    private final Amount currentBalance;

    /**
     * The operation of the ledger's line
     */
    private final Operation operation;

    /**
     * Nicely outputs the LedgerLine to a PrintStream.
     *
     * @param out the stream to output this object to
     */
    public void toOut(PrintStream out) {
        operation.toOut(out, currentBalance);
    }
}
