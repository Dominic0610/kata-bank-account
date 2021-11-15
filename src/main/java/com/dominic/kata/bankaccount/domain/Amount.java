package com.dominic.kata.bankaccount.domain;

import lombok.Value;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * A value object class for money amounts wrapping a BigDecimal.
 * Note that this representation fits the requirements (method {@link #moneyFormat() moneyFormat})
 * only for countries having a 2-digit decimal currency - most of them nowadays.
 * <p/>
 * Japan's Yen does not have subdivisions anymore. The Dinar have 3 decimal places in many countries.
 * And Mauritania just does not have a decimal currency at all.
 * <p/>
 * If requirements change, this class should be refactored.
 */
@Value
public class Amount {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.00");

    /**
     * The amount value.
     */
    BigDecimal value;

    /**
     * Private constructor to forbid Lombok generation of a public one.
     * Should use {@link #of(BigDecimal)} or {@link #of(double)} to instantiate an amount client-side.
     *
     * @param value the value of the amount
     */
    private Amount(BigDecimal value) {
        this.value = value;
    }

    /**
     * Public factory method to return a new Amount.
     *
     * @param value the value of the amount
     * @return a new Amount instance
     */
    public static Amount of(BigDecimal value) {
        return new Amount(value);
    }

    /**
     * Public factory method to return a new Amount.
     *
     * @param value the value of the amount
     * @return a new Amount instance
     */
    public static Amount of(double value) {
        return new Amount(new BigDecimal(value));
    }

    /**
     * Utility method to add two amounts.
     *
     * @param amountToAdd an amount to add to this one.
     * @return a new Amount instance representing the sum of the two amounts.
     */
    public Amount add(Amount amountToAdd) {
        return Amount.of(this.value.add(amountToAdd.value));
    }

    /**
     * Utility method to subtract two amounts.
     *
     * @param amountToSubtract an amount to subtract from this one.
     * @return a new Amount instance representing the difference of the two amounts.
     */
    public Amount subtract(Amount amountToSubtract) {
        return Amount.of(this.value.subtract(amountToSubtract.value));
    }

    /**
     * Utility method to change the sign of an amount.
     *
     * @return a new Amount instance representing the same amount but with a different sign.
     */
    public Amount changeSign() {
        return Amount.of(value.negate());
    }

    /**
     * Utility method to get the absolute value of an amount.
     *
     * @return a new Amount instance representing the absolute value of this amount.
     */
    public Amount absoluteValue() {
        return value.compareTo(BigDecimal.ZERO) >= 0 ? this : Amount.of(value.negate());
    }

    /**
     * Returns a money representation of the amount for a 2-digit decimal currency.
     *
     * @return a String representing the money format
     */
    public String moneyFormat() {
        return DECIMAL_FORMAT.format(value);
    }
}
