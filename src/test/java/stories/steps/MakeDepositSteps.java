package stories.steps;

import com.dominic.kata.bankaccount.domain.Account;
import com.dominic.kata.bankaccount.domain.Amount;
import com.dominic.kata.bankaccount.domain.Ledger;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.mockito.InOrder;
import org.mockito.Mockito;

import java.io.PrintStream;
import java.util.Date;

import static org.mockito.Mockito.mock;

public class MakeDepositSteps {
    protected Account account;
    protected final PrintStream out = mock(PrintStream.class);

    @BeforeStory
    public void beforeStoryDo() {
        account = new Account(new Ledger());
    }

    @Given("a client makes a deposit of $value on $date")
    public void givenAClientMakesADepositOf(Amount value, Date date) {
        account.deposit(value, date);
    }

    @When("he prints his bank operation history")
    public void whenHePrintsHisBankOperationHistory() {
        account.printAccountHistory(out);
    }

    @Then("he would see the deposit $accountHistory")
    public void heWouldSeetheDeposit(String accountHistory) {
        InOrder inOrder = Mockito.inOrder(out);
        inOrder.verify(out).println("DEPOSIT    | 14/11/2021 23:42:00 |           42.00 |           42.00");
    }

}
