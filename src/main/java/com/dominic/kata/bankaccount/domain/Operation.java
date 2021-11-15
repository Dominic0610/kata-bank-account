package com.dominic.kata.bankaccount.domain;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * An operation is a deposit or withdrawal performed at some point in time.
 * If the amount of the operation is positive, it's a credit (deposit) operation,
 * otherwise it's a debit (withdrawal) one. There's no reason with our specifications
 * to use a greater date precision like Timestamp.
 * <p/>
 * This class knows how to format itself to have a nice display.
 */
@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class Operation {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    /**
     * The amount of the operation. Negative for withdrawals.
     */
    private final Amount amount;

    /**
     * The timestamp when this operation is performed.
     */
    private final Date date;

    /**
     * Compute a new account balance applying this operation to the current account balance.
     *
     * @param accountBalance an account balance to start from
     * @return the new account balance after applying this operation to it
     */
    public Amount applyTo(Amount accountBalance) {
        return accountBalance.add(amount);
    }

    /**
     * Prints to the out stream a nicely formatted version of this operation, starting from the account balance.
     *
     * @param out            the output stream to print to
     * @param currentBalance a current balance which should be part of the nicely formatted output
     */
    public void toOut(PrintStream out, Amount currentBalance) {
        StringBuilder sb = new StringBuilder();

        // (operation, date, amount, balance)
        addOperationTypeTo(sb);
        addDateTo(sb);
        addAmountTo(sb);
        addBalanceTo(sb, currentBalance);

        out.println(sb.toString());
    }

    /**
     * Left pads the amount with a variable number of spaces to display it on 15 characters.
     *
     * @param sb     the StringBuilder to append the formatted amount to
     * @param amount the amount to append to the StringBuilder
     */
    private void leftPadAndAddAmount(StringBuilder sb, Amount amount) {
        String amountStr = amount.moneyFormat();

        final int FIFTEEN = 15;
        sb.append(String.format("%1$" + FIFTEEN + "s", amountStr));
    }

    /**
     * Adds the left-padded currentBalance <i>after applying this operation</i> to the StringBuilder.
     *
     * @param sb             the StringBuilder to append the formatted amount to
     * @param currentBalance the current balance before this operation
     */
    private void addBalanceTo(StringBuilder sb, Amount currentBalance) {
        leftPadAndAddAmount(sb, currentBalance);
    }

    /**
     * Adds this left-padded amount to the StringBuilder and delimits it with a '|'.
     *
     * @param sb the StringBuilder to append the formatted amount to
     */
    private void addAmountTo(StringBuilder sb) {
        leftPadAndAddAmount(sb, amount);
        sb.append(" | ");
    }

    /**
     * Adds this date to the StringBuilder and delimits it with a '|'.
     *
     * @param sb the StringBuilder to append the formatted amount to
     */
    private void addDateTo(StringBuilder sb) {
        sb.append(DATE_FORMAT.format(date));
        sb.append(" | ");
    }

    /**
     * Adds this operation type to the StringBuilder and delimits it with a '|'.
     * The operation type (DEPOSIT or WITHDRAWAL) is computed considering the sign of the amount.
     *
     * @param sb the StringBuilder to append the formatted amount to
     */
    private void addOperationTypeTo(StringBuilder sb) {
        sb.append(amount.getValue().compareTo(BigDecimal.ZERO) > 0 ?
                "DEPOSIT    | " :
                "WITHDRAWAL | ");
    }

}
