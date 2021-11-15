package stories.steps;

import org.jbehave.core.annotations.Then;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class MakeWithdrawalSteps extends GeneralOperationSteps {

    @Then("he would see the withdrawal $accountHistory")
    public void heWouldSeetheWithdrawal(String accountHistory) {
        InOrder inOrder = Mockito.inOrder(out);
        inOrder.verify(out).println("WITHDRAWAL | 14/11/2021 00:00:00 |          -42.00 |          -42.00");
    }
}
