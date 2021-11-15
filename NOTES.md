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