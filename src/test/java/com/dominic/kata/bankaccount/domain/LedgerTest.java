package com.dominic.kata.bankaccount.domain;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

//@RunWith(MockitoJUnitRunner.class)
public class LedgerTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    private Ledger ledger;

    @Before
    public void setUp() {
        ledger = new Ledger();
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test
    public void out() {
        // Just test that redirections in @Before are OK.
        System.out.print("hello");
        Assert.assertEquals("hello", outContent.toString());
    }

    @Test
    public void err() {
        // Just test that redirections in @Before are OK.
        System.err.print("hello again");
        Assert.assertEquals("hello again", errContent.toString());
    }

    @Test
    public void
    a_positive_amount_should_print_deposit() throws ParseException {
        // Arrange
        Operation operation = new Operation(
                Amount.of(42),
                new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse("12/12/2000T23:42:00"));
        ledger.addLedgerLine(Amount.of(0), operation);

        // Act
        ledger.toOut(System.out);

        // Assert
        assertThat(
                "DEPOSIT    | 12/12/2000 23:42:00 |           42.00 |            0.00",
                is(equalTo(outContent.toString().replaceAll("([\\r\\n])", ""))));
    }

    @Test
    public void
    a_negative_amount_should_print_withdrawal() throws ParseException {
        // Arrange
        Operation operation = new Operation(
                Amount.of(-42),
                new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse("12/12/2000T23:42:00"));
        ledger.addLedgerLine(Amount.of(42), operation);

        // Act
        ledger.toOut(System.out);

        // Assert
        assertThat(
                "WITHDRAWAL | 12/12/2000 23:42:00 |          -42.00 |           42.00",
                is(equalTo(outContent.toString().replaceAll("([\\r\\n])", ""))));
    }

    @Test
    public void
    should_print_two_deposits_and_a_withdrawal_in_reverse_order() throws ParseException {
        // Arrange
        Operation operation = new Operation(
                Amount.of(42),
                new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse("12/12/2000T23:42:00"));
        ledger.addLedgerLine(Amount.of(0), operation);

        Operation operation2 = new Operation(
                Amount.of(42),
                new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse("12/12/2000T23:43:00"));
        ledger.addLedgerLine(Amount.of(0), operation2);

        Operation operation3 = new Operation(
                Amount.of(-42),
                new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse("12/12/2000T23:44:00"));
        ledger.addLedgerLine(Amount.of(0), operation3);

        // Act
        ledger.toOut(System.out);

        // Assert
        assertThat(
                "WITHDRAWAL | 12/12/2000 23:44:00 |          -42.00 |            0.00" +
                        "DEPOSIT    | 12/12/2000 23:43:00 |           42.00 |            0.00" +
                        "DEPOSIT    | 12/12/2000 23:42:00 |           42.00 |            0.00",
                is(equalTo(outContent.toString().replaceAll("([\\n\\r])", ""))));
    }

}
