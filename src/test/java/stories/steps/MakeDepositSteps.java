package stories.steps;

import org.jbehave.core.annotations.Then;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class MakeDepositSteps extends GeneralOperationSteps {

    @Then("he would see the deposit $accountHistory")
    public void heWouldSeetheDeposit(String accountHistory) {
        InOrder inOrder = Mockito.inOrder(out);
        inOrder.verify(out).println("DEPOSIT    | 14/11/2021 23:42:00 |           42.00 |           42.00");
    }

}
