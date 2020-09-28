//  Account.java
//  CS257 Example (adapted from Lewis & Loftus)
//  Kevin Sahr, April 1, 2014
//
//  Represents a bank account with basic services such as deposit
//  and withdraw.

import java.text.DecimalFormat;

public class Account {
	
	// // class variables

	// constants
	public static final double INTEREST_RATE = 0.035; // interest rate of 3.5%
	public static final long START_ACCT_NUM = 100000; // first account number

	// non-constant class variables
	private static long numAccounts = 0; // number of accounts created

	// // instance data
	private long acctNumber;
	private double balance = 0.0;
	private String name = "John Doe";
	
	private int pinNum = 0;				// Add an integer instance variable which will hold the PIN number associated with the account. The default value for this variable should be 0.
	private boolean isLoggedIn = false;	// Add an instance variable that will indicate whether or not the account is logged-in. The default value for this variable should be false.
	
	// // class service methods
	public static long numberOfAccounts() {
		return numAccounts;
	}

	// // class helper methods
	private static long newAcctNum() {
		long newNum = START_ACCT_NUM + numAccounts;
		numAccounts++;
		return newNum;
	}

	// // constructors
	public Account() {
		acctNumber = newAcctNum();
	}

	public Account(String name, double balance) {
		this.name = name;
		this.balance = balance;
		acctNumber = newAcctNum();
	}

	// // service instance methods

	// Deposits the specified amount into the account. Returns the
	// new balance.
	public double deposit(double amount) {
		balance += amount;
		return balance;
	}

	// Withdraws the specified amount from the account and applies
	// the fee. Returns the new balance.
	public double withdraw(double amount, double fee) {
		balance -= amount + fee;
		return balance;
	}

	// Adds interest to the account and returns the new balance.
	public double addInterest() {
		balance += (balance * INTEREST_RATE);
		return balance;
	}

	// Returns the current name on the account.
	public String getName() {
		return name;
	}

	// Returns the current account number on the account.
	public long getAcctNumber() {
		return acctNumber;
	}
	
	// Returns the current balance of the account.
	public double getBalance() {
		return balance;
	}
	
	// Sets the current balance of the account to a new value.
	public void setBalance(double balance) {
		this.balance = balance;
	}

	// Returns a one-line description of the account as a string.
	public String toString() {
		DecimalFormat fmt = new DecimalFormat("0.00");
		return (acctNumber + "\t" + name + "\t" + pinNum + "\t" + isLoggedIn + "\t\t$" + fmt.format(balance));
		
	}
	
	// Add a setter and a getter for the PIN instance variable you created in (1).
	public void setPIN(int pin) {
		pinNum = pin;
	}
	
	public int getPIN() {
		return pinNum;
	}
	
	// Add a getter (but no setter) for the logged-in instance variable you created in (2).
	public boolean getIsLoggedIn() {
		return isLoggedIn;
	}
	
	// Add a login service method which takes a single integer argument and returns whether or not the passed-in argument matches the current value of the PIN instance variable.
	// If the passwords match the method should also set the logged-in instance variable to true.
	
	public void login(int p) {
		if(p == pinNum) {
			isLoggedIn = true;
		}
	}
	
	// Add a logout service method which takes no arguments and sets the logged-in instance variable to false.
	public void logout() {
		isLoggedIn = false;
	}
	
}
