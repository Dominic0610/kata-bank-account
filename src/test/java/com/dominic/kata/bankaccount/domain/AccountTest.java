package com.dominic.kata.bankaccount.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    @Mock
    Ledger ledger;
    private Account account;

    @Before
    public void setUp() {
        account = new Account(ledger);
    }

    @Test
    public void should_add_a_deposit() {
        // Arrange
        Date depositDate = new Date();
        Amount depositAmount = Amount.of(42);
        Operation deposit = new Operation(depositAmount, depositDate);

        // Act
        account.deposit(depositAmount, depositDate);

        // Assert
        verify(ledger).addLedgerLine(
                depositAmount,
                deposit);
    }

    @Test
    public void should_add_a_withdrawal() {
        // Arrange
        Date withdrawalDate = new Date();
        Amount withdrawalAmount = Amount.of(42);
        Operation withdrawal = new Operation(withdrawalAmount.changeSign(), withdrawalDate);

        // Act
        account.withdrawal(withdrawalAmount, withdrawalDate);

        // Assert
        verify(ledger).addLedgerLine(
                withdrawalAmount.changeSign(),
                withdrawal);
    }
}
