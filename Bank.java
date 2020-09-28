/*
 * Sam Platt
 * CS 257
 * Written Feb 6, 2020
 */

public class Bank {
	
	// Class Variables
	private Account[] listOfAccounts;
	
	// Instance Variables
	
	/* 
	 * A constructor that takes as its single argument the maximum number of accounts.
	 * The constructor should allocate an (empty) accounts array with the specified size.
	 */
	public Bank(int max) {
		listOfAccounts = new Account[max];
		
	}
	
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
		long accNumbear = -1; // The best variable names are puns
		int i = (int)Account.numberOfAccounts();
		
		if(i < listOfAccounts.length) {
			
			// Create an Account
			listOfAccounts[i] = new Account(accName, 0); // sets the account name and balance
			listOfAccounts[i].setPIN(0); // sets the PIN
				
			accNumbear = listOfAccounts[i].getAcctNumber();
		}
		
		return accNumbear;
	}

}
