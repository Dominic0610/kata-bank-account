Narrative:
In order to save money
As a bank client
I want to make a deposit in my account

Scenario: Deposit
Given a client makes a deposit of 42 on 2021-11-14 23:42:00
When he prints his bank operation history
Then he would see the deposit
DEPOSIT    | 14/11/2021 23:42:00 |           42.00 |           42.00
