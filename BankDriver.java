/*
 * Sam Platt
 * CS 257
 * Written Feb 11, 2020
 */

public class BankDriver {
	
	public static void main(String args[]) {
		// Create a single Bank object with a maximum of 4 accounts.
		Bank banco = new Bank(4);
		
		// Create an ATM with the Bank you created in (1), named "ATM 1".
		ATM machine1 = new ATM(banco, "ATM 1");
		
		// Create a second ATM with the Bank you created in (1), named "ATM 2".
		ATM machine2 = new ATM(banco, "ATM 2");
		
		// Invoke the mainMenu method on the first ATM.
		machine1.mainMenu();
		
		// Print-out the state of the Bank object.
		System.out.println(banco.toString());
		
		// Invoke the mainMenu method on the second ATM.
		machine2.mainMenu();
		
		// Print out the state of the Bank object.
		System.out.println(banco.toString());
	}
}
