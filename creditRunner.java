import java.text.DecimalFormat;
import java.util.Scanner;

public class creditRunner {

	// This class is the main program to create and test account functionality
	// The console will ask for input and create account based on that input.
	// Then a menu will appear asking what you want to do with your account
	// You can charge, make a payment, display current amount owed on a given day, or exit
	
	// Includes logic so charges greater than credit limit will not work, and 
	// payments that take account to less than 0.00 owed will not work.
	
	// I assumed opening day will be entered as day 1, not day 0.
	// This means for scenario 2, 15 days after the first day will be day 16, not day 15.
	// And 25 days after day 1 will be day 26, not day 25.
	
	// Includes helper methods to validate all input.
	// Valid input for name: Must either include first and last name
	// or first middle and last. Cannot include any numbers
	// Valid input for numbers: Must be greater than or equal to 0.
	// Can't include any letters
	// If any input is not valid, program will ask again for a valid input
	
	
	// Helper method for error handling on name entry
	public static String getName(Scanner in) {
		boolean validated = false;
		String name = in.nextLine();
		while(!validated) {
			try {
			// If string contains a number, throw an exception
				for(int i = 0; i < name.length(); i++) {
					if (Character.isDigit(name.charAt(i))) {
						throw new Exception();
					}
				}
				String[] split = name.split(" ");
				// If name is not 2 or 3 words long, throw an exception
				if(split.length != 2 && split.length != 3) {
					throw new Exception();
				}
				validated = true;
				return name;
			} catch (Exception e) {
				// If exception is thrown, ask for a new valid name
				System.out.println("Please enter a valid name (no numbers, must include first, middle (optional), last): ");
				name = in.nextLine();
			}
		}
		// Won't ever be reached since the while loop runs until valid input
		return null;
	}
	
	// Helper method for error handling on floats
	public static float getFloat(Scanner in) {
		boolean validated = false;
		String input = in.nextLine();
		float f;
		// Loop to keep asking for input until valid number is entered
		while(!validated) {
			try {
				// If input string contains a letter, throw an exception
				for (int i = 0; i < input.length(); i++) {
					if(Character.isLetter(input.charAt(i))) {
						throw new Exception();
					}
				}
				// Change input string to float
				f = Float.parseFloat(input);
				// If float is less than 0, throw an exception
				if (f < 0) {
					throw new Exception();
				}
				validated = true;
				return f;
			} catch (Exception e) {
				// If exception is thrown, ask for a new number
				System.out.println("Please enter a valid number: ");
				input = in.nextLine();
			}
		}
		// Won't ever be reached since the while loop runs until valid input
		return 0.0f;
	}
	
	// Helper method for error handling on ints
	public static int getInt(Scanner in) {
		boolean validated = false;
		String input = in.nextLine();
		int i;
		// Loop to keep asking for input until valid number is entered
		while(!validated) {
			try {
				// If input string contains a letter, throw an exception
				for (int j = 0; j < input.length(); j++) {
					if(Character.isLetter(input.charAt(j))) {
						throw new Exception();
					}
				}
				// Change input string to int
				i = Integer.parseInt(input);
				// If int is less than 0, throw an exception
				if (i < 0) {
					throw new Exception();
				}
				validated = true;
				return i;
			} catch (Exception e) {
				// If exception is thrown, ask for a new number
				System.out.println("Please enter a valid number: ");
				input = in.nextLine();
			}
		}
		// Won't ever be reached since the while loop runs until valid input
		return 0;
	}
	
	
	public static void main(String[] args) {
		
		// Get account details
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter new account holder's name: ");
		String name = getName(in);
		System.out.println("Please enter APR for account (in percent): ");
		float APR = getFloat(in) / 100;
		System.out.println("Please enter credit limit for account: ");
		float limit = getFloat(in);

		System.out.println("");
		System.out.println("");
		System.out.println("");
		
		// Create account object
		Account a = new Account(name, APR, limit);
		
		// function that creates menu for user options
		createMenu(a, in);
		
	}
	
	public static void createMenu(Account a, Scanner in) {
		// value to switch on for menu
		int select;
		// Decimal formatter to round dollar amount to two decimals
		DecimalFormat f = new DecimalFormat("##.00");
		do {

			System.out.println("Welcome to Avant Credit. Please select the number of the option you want to perform: ");
			System.out.println("Charge your account: 1");
			System.out.println("Make a payment: 2");
			System.out.println("Print your accounts balance: 3");
			System.out.println("Exit menu: 4");
			select = getInt(in);
			switch (select) {
			
				// If 1 is chosen ask for amount and day of charge and store details in account
				case 1:
					System.out.println("Enter amount you want to charge: ");
					float charge = getFloat(in);
					System.out.println("Enter day of charge: ");
					int chargeDay = getInt(in);
					
					// If current amount plus charge is greater than credit limit, break
					if (a.getCurrentAmount() + charge > a.getLimit()) {
						System.out.println("Credit limit reached or exceeded, charge not recorded");
						System.out.println("");
						break;
					}
					
					a.addCharge(chargeDay, charge);
					a.addCurrentAmount(charge);
					System.out.println("Charge recorded");
					System.out.println("");
					break;
					
				// if 2 is chosen, ask for amount and day of payment and store details in account 
				case 2:
					System.out.println("Enter amount you want to pay: ");
					float payment = getFloat(in);
					System.out.println("Enter day of payment: ");
					int paymentDay = getInt(in);
					
					// If current amount minus payment is less than 0, break
					if (a.getCurrentAmount() - payment < 0) {
						System.out.println("Account cannot go below 0, payment not recorded");
						System.out.println("");
						break;
					}
					
					a.addPayment(paymentDay, payment);
					a.addCurrentAmount(-payment);
					System.out.println("Payment recorded");
					System.out.println("");
					break;
					
				// If 3 is chosen, print out account name and money owed based on day user inputs
				case 3:
					System.out.println("What day would you like to see the outstanding balance?: ");
					int daysOpen = getInt(in);
					float amountOwed = a.calculateMoneyOwed(daysOpen);
					System.out.println("Total amount owed on " + a.getName() + "'s account on day " + daysOpen + ": $" + f.format(amountOwed));
					System.out.println("");
					break;
					
				// If 4 is chosen, exit loop and end program
				case 4:
					System.out.println("Have a nice day!");
					System.out.println("");
					break;
				
				// If any other number is chosen, ask for a new number
				default:
					System.out.println("Please enter a number 1 through 4");
					System.out.println("");
					break;
			}
		} while (select != 4);
	}
}
