package com.dominic.kata.bankaccount.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LedgerLineTest {

    @Mock
    PrintStream out;

    @Test
    public void
    should_output_a_deposit() throws ParseException {
        // Arrange
        Operation operation = new Operation(
                Amount.of(42),
                new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse("12/12/2000T23:42:00"));
        LedgerLine ledgerLine = new LedgerLine(
                Amount.of(100),
                operation
        );

        // Act
        ledgerLine.toOut(out);

        // Assert
        verify(out).println("DEPOSIT    | 12/12/2000 23:42:00 |           42.00 |          100.00");
    }

    @Test
    public void
    should_output_a_withdrawal() throws ParseException {
        // Arrange
        Operation operation = new Operation(
                Amount.of(-42),
                new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse("12/12/2000T23:42:00"));
        LedgerLine ledgerLine = new LedgerLine(
                Amount.of(100),
                operation
        );

        // Act
        ledgerLine.toOut(out);

        // Assert
        verify(out).println("WITHDRAWAL | 12/12/2000 23:42:00 |          -42.00 |          100.00");
    }

}
