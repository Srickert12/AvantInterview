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
