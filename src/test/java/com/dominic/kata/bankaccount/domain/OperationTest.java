package com.dominic.kata.bankaccount.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class OperationTest {

    @Mock
    PrintStream outStream;

    @Test
    public void should_calculate_new_balance_after_deposit() {
        // Given a deposit (i.e. an Operation with a positive amount) and a current balance of one hundred...
        Operation operation = new Operation(Amount.of(42), new Date());
        Amount currentBalance = Amount.of(100);

        // When operation is applied to the current balance...
        Amount newBalance = operation.applyTo(currentBalance);

        // ... it should return one hundred forty-two.
        assertThat(Amount.of(142), is(equalTo(newBalance)));
    }

    @Test
    public void should_calculate_new_balance_after_withdrawal() {
        // Given a withdrawal (i.e. an Operation with a negative amount) and a current balance of one hundred...
        Operation operation = new Operation(Amount.of(-42), new Date());
        Amount currentBalance = Amount.of(100);

        // When operation is applied to the current balance...
        Amount newBalance = operation.applyTo(currentBalance);

        // ... it should return the correct result.
        assertThat(Amount.of(58.0), is(equalTo(newBalance)));
    }

    @Test
    public void deposit_should_print_itself_correctly() throws ParseException {
        Operation operation = new Operation(
                Amount.of(42),
                new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse("12/12/2000T23:42:00"));
        Amount currentBalance = Amount.of(100);

        operation.toOut(outStream, currentBalance);

        verify(outStream).println("DEPOSIT    | 12/12/2000 23:42:00 |           42.00 |          100.00");
    }

    @Test
    public void withdrawal_should_print_itself_correctly() throws ParseException {
        Operation operation = new Operation(
                Amount.of(-42),
                new SimpleDateFormat("dd/MM/yyyy'T'HH:mm:ss").parse("12/12/2000T23:42:00"));
        Amount currentBalance = Amount.of(100);

        operation.toOut(outStream, currentBalance);

        verify(outStream).println("WITHDRAWAL | 12/12/2000 23:42:00 |          -42.00 |          100.00");
    }
}
