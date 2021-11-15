package com.dominic.kata.bankaccount.domain;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * This class keeps the Account history as a list of LedgerLine.
 */
public class Ledger {

    /**
     * The header, to be printed with the list of operations.
     */
    public static final String HEADER =
            "Operation  | Date                |      Op. amount |         Balance";

    /**
     * A list of LedgerLine, i.e. of operations and current balances.
     */
    List<LedgerLine> operations = new ArrayList<>();

    /**
     * Adds a new entry to the list of ledger lines.
     *
     * @param currentBalance the current balance to be added to the Ledger
     * @param operation      the operation to be added to the Ledger
     */
    public void addLedgerLine(Amount currentBalance, Operation operation) {
        operations.add(new LedgerLine(currentBalance, operation));
    }

    /**
     * Display the history, in a reverse order (most recent operation first).
     *
     * @param out a PrintStream where the output should go
     */
    public void toOut(PrintStream out) {
        for (int i = operations.size() - 1; i >= 0; i--) {
            operations.get(i).toOut(out);
        }
    }

    /**
     * Displays the header. Clients of this class are free to use it or not.
     *
     * @param out a PrintStream where the output should go
     */
    public void headerToOut(PrintStream out) {
        out.println(HEADER);
    }
}
