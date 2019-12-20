Assignment: Create a software tool (command line is fine) that completes the following requirements

Given a credit card that functions as follows: 
  Each card has an APR and Credit Limit.
  Interest is calculated daily, starting the day after the account is opened, at the close of each day.
  Calculated interest becomes due at the close every 30 days after the account has been opened.
  e.g., asking for the total outstanding balance 15, 28, or 29 days after opening will give the outstanding balance, but  asking for balance 30 days after opening will give the outstanding balance plus the accrued interest.

The software should be able to:
  Create an account (e.g. opening a new credit card)
  Keep track of charges (e.g. card swipes)
  Keep track of payments
  Provide the total outstanding balance as of any given day

Test Scenario 1
  A customer opens a credit card with a $1,000.00 limit at a 35% APR.
  The customer charges $500 on opening day (outstanding balance becomes $500).
  The total outstanding balance owed 30 days after opening should be $514.38.
  500 * (0.35 / 365) * 30 = 14.38 

Test Scenario 2 
  A customer opens a credit card with a $1,000.00 limit at a 35% APR.
  The customer charges $500 on opening day (outstanding balance becomes $500).
  15 days after opening, the customer pays $200 (outstanding balance becomes $300).
  25 days after opening, the customer charges another $100 (outstanding balance becomes $400).
  The total outstanding balance owed 30 days after opening should be $411.99.
  (500 * 0.35 / 365 * 15) + (300 * 0.35 / 365 * 10) + (400 * 0.35 / 365 * 5) = 11.99


I completed the assignment in java.

To run, you must have java installed and compile both java files (Account.java and creditRunner.java)

To do this run the java compile command on the command line:

javac Account.java
javac creditRunner.java

Then run the creditRunner file to run the program with the following command on the command line:

java creditRunner

Features:
  - Correctly outputs amount owed for both test scenarios, and all other scenarios as well
  - Includes error handling on all inputs, so if input isn't in the right format, program will ask you
    to input a new value that is in the right format
  - Includes ability to create as many charges and payments as you want
  - The program will not let the user charge more than the credit limit
  - The program will not let the user pay more than they owe


Assumptions:
  - In test scenario 2, it says on day 1 $500 was charged then 15 days after that, $200 was payed.
    This means that the payment should be on day 16, not day 15 since 15 days after day 1 is day 16.
    Similarly, the $100 charge 25 days after the first day should be day 26, not day 25.
    In my program, all test scenarios should be formatted like this
