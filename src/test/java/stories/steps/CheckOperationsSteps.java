package stories.steps;

import com.dominic.kata.bankaccount.domain.Amount;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.util.Date;

public class CheckOperationsSteps extends GeneralOperationSteps {

    @Given("another deposit of $value on $date")
    public void anotherDepositOf(Amount value, Date date) {
        account.deposit(value, date);
    }

    @Given("a withdrawal of $value on $date")
    public void aWithdrawalOf(Amount value, Date date) {
        account.withdrawal(value, date);
    }

    @Then("he would see the account history $accountHistory")
    public void heWouldSeetheAccountHistory(String accountHistory) {
        InOrder inOrder = Mockito.inOrder(out);
        inOrder.verify(out).println("WITHDRAWAL | 16/11/2021 17:42:00 |         -100.00 |          301.00");
        inOrder.verify(out).println("DEPOSIT    | 15/11/2021 23:42:00 |          200.50 |          401.00");
        inOrder.verify(out).println("DEPOSIT    | 14/11/2021 23:42:00 |          200.50 |          200.50");
    }
}
