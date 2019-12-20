import java.util.HashMap;
import java.util.Map;

// This is the account class used to create account objects
// Stores all fields, charges, and payments
// Has methods to add payments and charges, calculate total money owed
// and getters for fields

public class Account {
	// Account fields
	private String accountHolder;
	private float APR;
	private float creditLimit;
	private float currentAmount;
	
	// Hashmaps to store day of charge/payment and amount
	private	HashMap<Integer, Float> charges;
	private HashMap<Integer, Float> payments;
	
	// Constructor
	Account(String accountHolder, float APR, float creditLimit) {
		this.accountHolder = accountHolder;
		this.APR = APR;
		this.creditLimit = creditLimit;
		currentAmount = 0;
		payments = new HashMap<Integer, Float>();
		charges = new HashMap<Integer, Float>();
	}
	
	// Add a charge to charges hashmap
	public void addCharge(int day, float amount) {
		charges.put(day, amount);
	}
	
	// Add a payment to payments hashmap
	public void addPayment(int day, float amount) {
		payments.put(day, amount);
	}
	
	// Getters and setters to make sure amount doesnt go above credit limit
	// Or below 0
	// Current amount variable is only for this error handling, is not used in
	// any calculations
	public float getCurrentAmount() {
		return currentAmount;
	}
	
	public void addCurrentAmount(float n) {
		currentAmount += n;
	}
	
	// Getter for name
	public String getName() {
		return accountHolder;
	}
	
	// Getter for credit limit
	public float getLimit() {
		return creditLimit;
	}
	
	// Function to calculate amount of money owed
	public float calculateMoneyOwed(int daysOpen) {
		float totalAmount = 0;
		float APRsum = 0;
		
		// Loop through days account has been open
		for(int i = 1; i <= daysOpen; i++) {
			
			int dailyAmount = 0;
			// Loop through charge dictionary, checking if there is a charge on this day
			// If there is, add it to the daily amount
			for (Map.Entry<Integer, Float> charge : charges.entrySet()) {
				if (charge.getKey() == i) {
					dailyAmount += charge.getValue();
				}
			}
			// Loop through payment dictionary, checking if there is a payment on this day
			// If there is, subtract it from the daily amount
			for (Map.Entry<Integer, Float> payment : payments.entrySet()) {
				if (payment.getKey() == i) {
					dailyAmount -= payment.getValue();
				}
			}
			
			// Add amount for this day to total amount
			totalAmount += dailyAmount;
						
			// If days open is greater than 30, add this days interest to APRsum
			if (daysOpen >= 30) {
				APRsum += totalAmount * (APR / 365);
			}
			
		}
		// Return the total amount plus the sum of all APR
		// APRsum will be 0 if days open is less than 30
		return totalAmount + APRsum;
		
	}
	
}
