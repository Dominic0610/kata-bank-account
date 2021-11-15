# NOTES: Quick thoughts about the kata implementation

NB: Every point mentioned below is accompanied by at least one Git commit/push.

## Which dependencies ?
There are 3 user stories in the kata. We clearly need a BDD testing framework:
- jbehave https://jbehave.org/
- Cucumber https://cucumber.io/
- Cucumber-JVM https://cucumber.io/docs/installation/java/
- Other?

Having some experience with the first one, I'll pick it. May try the others later. New Git branch: *jbehave*.\
Add other useful but minimal dependencies like *Lombok*.\
No persistence, minimal implementation => forget about *Spring* for now.

##Domain
All right. We have our jbehave framework, and we can see it works: `mvn integration-test`.\
Our stories are just narratives for now. If we want frequent pushes and avoid pushing failing tests,
we don't want to write scenarios and the BDD test classes right away: there would be too much code for the next push.\
So just focus on the domain objects, and commit them one by one along their JUnit tests.

- ###Amount
A simple wrapper for a BigDecimal.

This might be a major drawback with non-decimal currencies: https://en.wikipedia.org/wiki/Non-decimal_currency.
What about 1 Galleon = 17 Sickles = 493 Knuts in Harry Potter?\

The good news is that this special case is rare in real-world:
only Mauritania and Madagascar still have non-decimal currencies -
https://en-academic.com/dic.nsf/enwiki/658767.

- ###Operation
It wraps an Amount and a Date and knows how to display itself nicely. As we need to test the output, a mock is necessary
and so we add Mockito dependency.

- ###LedgerLine
An Account - not created yet - should be able to display its own operation history. An object 'AccountHistory' will keep
track of this. Let's call it 'Ledger'.

Every account movement is tracked in a list of the Ledger. This is the LedgerLine object, composed by an Operation and
a current balance amount.

- ###Ledger
Already mentioned above. Just a list of LedgerLine knowing how to print a header and its operations.

- ###Account
Finally, here's the Account class, composed of a balance and a Ledger. It knows how to perform operations as deposit and withdrawal. It knows
how to display the operation history.

The domain classes are implemented at this point and all the 26 JUnit tests pass. Type `mvn test` to see results.

# jBehave - acceptance tests
- ### Deposit
Back to jBehave and first test (deposit) implementation. Some technical issues related to jBehave incompatibility with
Java 17, see HOWTO for more on this.
Given that the Account deposit() method requires an Amount and a Date, converters shall be used.

- ### Withdrawal
Implementation similar to the Deposit. Refactor the Step classes to inherit from a same parent in order to share code.

- ### Print account history
A final jBehave acceptance test which looks like this:
```
Narrative:
In order to check my operations
As a bank client
I want to see the history (operation, date, amount, balance) of my operations

Scenario: Deposit twice, make a withdrawal, print account history
Given a client makes a deposit of 200.50 on 2021-11-14 23:42:00
And another deposit of 200.50 on 2021-11-15 23:42:00
And a withdrawal of 100 on 2021-11-16 17:42:00
When he prints his bank operation history
Then he would see the account history
WITHDRAWAL | 16/11/2021 17:42:00 |         -100.00 |          301.00
DEPOSIT    | 15/11/2021 23:42:00 |          200.50 |          401.00
DEPOSIT    | 14/11/2021 23:42:00 |          200.50 |          200.50
```

## Service API ?
Well, the Account class is nearly this. But we can do better: a service should live in its own tier and expose a
nice interface. The class
- #### BankAccountService
does just this. It has the following methods:
- deposit
- withdrawal
- printAccountHistory with no arguments to print to System.out
- printAccountHistory with a PrintStream parameter to print to that stream

BankAccountService also use doubles for the amounts, even if they are stored as BigDecimal in the Account class (and all
operations are performed on BigDecimal, without any rounding, except the print history). There's no reason to have a
greater precision for a deposit/withdrawal API.

A client implementation (yes, it was *not* required; but it's minimalistic, and it was useful to interactively test the app)
is provided. See the HOWTO.md file for instructions.