/*
 * Sam Platt
 * CS 257
 * Written Feb 6, 2020
 */

import java.util.Random;

public class Bank {
	
	// Class Variables
	
	public static final int UPPER_BOUND = 99;
	public static final int LOWER_BOUND = 1;
	
	private Account[] listOfAccounts;
	
	// Instance Variables
	
	private int numAccounts;
	
	// Constructors
	
	/* 
	 * A constructor that takes as its single argument the maximum number of accounts.
	 * The constructor should allocate an (empty) accounts array with the specified size.
	 */
	public Bank(int max) {
		listOfAccounts = new Account[max];
		
	}
	
	// Methods
	
	/*
	 * Return a String containing the toString() of all currently existing Accounts, one Account per line.
	 */
	public String toString() {
		String concatToString = "";
		
		// for loop that concatenates the string to include each additional Account's .toString() information.
		for(int i = 0; i < Account.numberOfAccounts(); i++) {
			if(concatToString.length() > 0)  {
				concatToString = concatToString + listOfAccounts[i].toString() + "\n";
			} else {
				concatToString = listOfAccounts[i].toString() + "\n";
			}
			
		}
		
		return concatToString;
	}
	
	/*
	 * This method should return the Account with the indicated Account number, or null if the Account does not exist.
	 * Hint: note the relationship between account numbers and their array indexes as they are created in createAccount.
	 */
	public Account getAccount(long accNum) {
		Account acct = null; // the default value to return if you never find a match.
		
		// Loop through the accounts in listOfAccounts
		for(int i = 0; i < Account.numberOfAccounts(); i++) {
			
			// Check if the account number matches the input.
			if(accNum == listOfAccounts[i].getAcctNumber()) {
				acct = listOfAccounts[i];
				
				break;
			}
		}
		return acct;
	}
	
	/*
	 * This method should create a new Account using the name specified argument, an initial balance of $0.00, 
	 * and an initial pin number of 0.
	 * 
	 * The method should add the new Account to the array of Accounts, doing any book-keeping that might be necessary.
	 * 
	 * The method should return the Account number of the newly created Account, 
	 * or -1 if for any reason the Account creation failed (for example, if the Account array is already full)
	 */
	public long createAccount(String accName) {
		long accNumber = -1;
		
		if(numAccounts < listOfAccounts.length) {
			
			// Create an Account
			listOfAccounts[numAccounts] = new Account(accName, 0); // sets the account name and balance
			listOfAccounts[numAccounts].setPIN(0); // sets the PIN
				
			accNumber = listOfAccounts[numAccounts].getAcctNumber();
			
			numAccounts++;
		}
		
		return accNumber;
	}
	
	/*
	 * This method should implement the selection sort pseudocode given in class
	 * (comparing elements using the Account class compareTo method)
	 * to sort the elements in the accountArray argument by account balance (from smallest to largest)
	 */
	private static void sortAccountArray(Account[] accountArray) {
		int lastIndex = accountArray.length - 1; // lastIndex is the number of elements in the List - 1.
		
		// outerIndex is the first element in the unsorted portion
		for (int outerIndex = 0; outerIndex <= lastIndex; outerIndex++) { // for each outerIndex from 0 through lastIndex
			
			int minIndex = outerIndex; // assume first value is min
			
			for(int innerIndex = outerIndex + 1; innerIndex <= lastIndex; innerIndex++) { // for each innerIndex from (outerIndex + 1) through lastIndex
				
				if(accountArray[innerIndex].compareTo(accountArray[minIndex]) == -1) { // if lipsumList[innerIndex] < lipsumList[minIndex]
					/*
					 * REMINDER:
					 * Outside value (left side) is less than inside value (right side), 	returns -1
					 * Outside value (left side) is equal to inside value (right side), 	returns 0
					 * Outside value (left side) is greater than inside value (right side), returns 1
					 */
					
					minIndex = innerIndex; // found new min
					
				}
			}
			
			// swap lipsumList[minIndex] and lipsumList[outerIndex]
			
			Account temp = accountArray[outerIndex];
			
			accountArray[outerIndex] = accountArray[minIndex];
			accountArray[minIndex] = temp;
		}
	}
	
	/*
	 * This method will build and return a String containing all of the Account descriptions, formatted one-per-line.
	 * But unlike toString, the String returned by this method will have the accounts in order by their balances (from smallest to largest).
	 */
	public String toStringSorted() {
		
		// Create a local variable array of Accounts exactly big enough to hold the actual (instance variable) Account array elements.
		int arrayLength = numAccounts;
		Account[] sortedStringArray = new Account[arrayLength];
		
		
		// Have each element in this local variable array point to the object with the same index in the actual Account array (remember that the elements in both arrays are actually references to Account objects).
		for(int i = 0; i < arrayLength; i++) {
			sortedStringArray[i] = listOfAccounts[i];
			
		}
		
		// Sort the local variable array using your sortAccountArray method. Note that if everything is implemented correctly, this should have no effect on the actual (instance variable) Account array. You will merely be rearranging what the references in the local variable array point to, not altering the objects themselves.
		sortAccountArray(sortedStringArray);
		
		// Build and return a String containing the Account descriptions in order of the sorted local variable array.
		String sortedDescriptions = null;
		for(int i = 0; i < arrayLength; i++) {
			
			if(i != 0) { // This if/else statement prevents "null" from showing up on the first line.
				sortedDescriptions += "\n" + sortedStringArray[i].toString();
				
			} else {
				sortedDescriptions = sortedStringArray[i].toString();
			}
		}
		
		return sortedDescriptions;
		
	}
	
	
	/*
	 * This method should generate and add num new accounts to the accounts array (pre-existing accounts should remain unchanged). 
	 * The name for each new account should be set to “Acct #N”, where N is the index of the account in the accounts array. 
	 * The starting balance for each new account should be set to a random integer between 1 (inclusive) and 100 (exclusive).
	 */
	public void testPopulate(int num) {
		int accountsToAdd = num;
		
		for(int i = 0; i < accountsToAdd; i++) {
			int accNum = numAccounts;
			String name = "Acct #" + accNum;
			
			createAccount(name);
			
			// Generate a starting balance for the account. Random integer between 1 (inclusive) and 100 (exclusive).
			Random randNum = new Random();
			double rnd = randNum.nextInt(UPPER_BOUND - LOWER_BOUND) + LOWER_BOUND;
			listOfAccounts[accNum].setBalance(rnd);
			
		}
		
	}

}
