/*
 * Sam Platt
 * CS 257
 * Written 20 Feb, 2020
 */

public class Driver {
	
	// CLASS VARIABLES
	
	// INSTANCE VARIABLES
	
	// METHODS
	
	public static void main(String args[]) {
		
		/*
		 
		// Create 3 Account objects.
		
		Account acc0 = new Account("Account 0", 3);
		Account acc1 = new Account("Account 1", 4);
		Account acc2 = new Account("Account 2", 1);
	
		// Compare each object to each other.
		// Print out the results of these comparisons, along with the account balances, and verify that the results are as expected.
		
		System.out.println("acc0 compared to acc1: " + acc0.compareTo(acc1));
		System.out.println("acc0 compared to acc2: " + acc0.compareTo(acc2));
		System.out.println("acc1 compared to acc2: " + acc1.compareTo(acc2));
		System.out.println("acc0 balance: " + acc0.getBalance());
		System.out.println("acc1 balance: " + acc1.getBalance());
		System.out.println("acc2 balance: " + acc2.getBalance());
		
		*/
		
		// Create a single Bank object with a maximum of 20 accounts.
		Bank banco = new Bank(20);
		
		// Use the testPopulate method to add 5 accounts to the Bank.
		banco.testPopulate(5);
		
		// Print-out the Bank object state (using toString).
		System.out.println(banco.toString());
		System.out.println("\n");
		
		// Invoke the toStringSorted method on the Bank and output the returned String.
		System.out.println(banco.toStringSorted());
		System.out.println("\n");
		
		// Invoke the testPopulate method to add 3 more accounts to the Bank. 
		// At this point there should be a total of 8 accounts in the Bank.
		banco.testPopulate(3);
		
		// Again print-out the Bank object state (using toString).
		System.out.println(banco.toString());
		
		// Again invoke the toStringSorted method and output the returned String.
		System.out.println(banco.toStringSorted());
		System.out.println("\n");
		
	}
}
