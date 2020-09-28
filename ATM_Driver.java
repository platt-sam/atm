/*
 * Sam Platt
 * CS 257
 * Written Jan 28, 2020
 */
public class ATM_Driver {
	
	public static void main(String args[]) {
		/* 
		 * Create an Account with your name and an initial balance of $100.00
		 * Set the PIN on the account to 4444.
		 */
		Account account0 = new Account("sam", 100);
		account0.setPIN(4444);
		
		/*
		 * Create an ATM with the account you created in (1).
		 */
		ATM atm0 = new ATM(account0);
		
		atm0.mainMenu();
		
		atm0.mainMenu();
		
	}
	
}
