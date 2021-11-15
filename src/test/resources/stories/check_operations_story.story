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
