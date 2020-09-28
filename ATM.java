/*
 * Sam Platt
 * CS 257
 * Written Jan 23, 2020
 */

import java.util.Scanner;

public class ATM {
	String s;
	int i;
	double d;
	boolean doNotLogout = true;
	
	private static Scanner scan = new Scanner(System.in);
	
	// The class should have a single instance variable, which is the Account object that the ATM operates on.
	Account acc;
	
	// The class should have a single constructor that takes as its argument an Account object that will be stored in the ATM object instance variable.
	ATM(Account acc) {
		this.acc = acc;
	}
	
	public void mainMenu() {
		System.out.println("WELCOME TO THE MAIN MENU");
		
		do {
			System.out.println("Type 'login' to login to your ATM. Type 'exit' to exit this menu.");
			
			s = scan.nextLine();
			
			if(s.equals("login")) {
				/*
				 * Have the user input an integer PIN number and then call the login method on the account instance variable with the user-input PIN.
				 */
				
				do {
				
					System.out.println("What is your PIN?");
					s = scan.nextLine();
					i = Integer.parseInt(s);
					
					acc.login(i);
					
					// Give an informative error message.
					if(acc.getIsLoggedIn() == false) {
						System.out.println("Sorry, your PIN was incorrect.");
					}
				} while(acc.getIsLoggedIn() == false);
				
				// Invoke the accountMenu method.
				doNotLogout = true;
				accountMenu();
			}
			
		} while(!(s.equals("exit"))); // input does not equal "exit".
	}
	
	private void accountMenu() {
		do {
			System.out.println("Please enter your selection from the options below:\n\ta\tDeposit\n\tb\tWithdraw\n\tc\tAccount Info\n\td\tChange PIN\n\te\tLogout");
		
			s = scan.nextLine();
		
			if(s.equals("a")) {
				// Deposit.
				
				/* 
				 * Have the user input a deposit amount and perform the deposit.
				 * Validate that the user enters a positive deposit amount, giving them as many chances as necessary to do so.
				 */
				
				do {
					System.out.println("How much would you like to deposit?"); // Have the user input a deposit amount.
				
					s = scan.nextLine();
					d = Double.parseDouble(s);
				
					if(d > 0) { // Validate that the user enters a positive deposit amount.
						acc.deposit(d); // Perform the deposit.
						System.out.println("Your deposit was successful.");
						
					} else if(d < 0) {
						System.out.println("You cannot deposit a negative amount.\nIf you meant to withdraw money, deposit an amount of 0 to return to the Account Menu.");
					} else if(d == 0) {
						// This gives the user an option to return to the accountMenu (in case they meant to withdraw money).
						break;
					}
				} while (d < 0);
				
				continue; // This returns the user to the accountMenu.
			
			} else if(s.equals("b")) {
				// Withdraw.
				
				do {
					System.out.println("How much would you like to withdraw? (Note: there is a $1.50 service fee for valid withdrawals.)");
				
					s = scan.nextLine();
					d = Double.parseDouble(s);
				
					if(d > 0) {
						if(acc.getBalance() >= d + 1.5) {
							// Check if the balance is at least the sum of the withdrawal amount and the service fee.
							
							acc.withdraw(d, 1.5);
							
							System.out.println("Your withdrawal was successful.");
						} else {
							// If there is not enough money in the account.
							
							System.out.println("Error. Insufficient funds.");
							
							break;
						}
					} else if(d < 0) {
						System.out.println("You cannot withdraw a negative amount.\nIf you meant to deposit money, withdraw an amount of 0 to return to the Account Menu.");
					} else {
						// This gives the user an option to return to the accountMenu (in case they meant to deposit money).
						
						break;
					}
				} while(d < 0);
				
				continue; // This returns the user to the accountMenu.
				
			} else if(s.equals("c")) {
				// Account Info.
				
				System.out.println(acc.toString());
				
				System.out.println("You successfully viewed your account info.");
				
				continue; // This returns the user to the accountMenu.
				
			} else if(s.equals("d")) {
				// Change PIN.
				
				do {
					System.out.println("What would you like to change your PIN to?");
					
					s = scan.nextLine();
					i = Integer.parseInt(s);
					
					if(i < 10000 && i >= 0) {
						acc.setPIN(i);
						
						System.out.println("You successfully changed your PIN.");
						
						i = 0;
						
					}
					
				} while(i > 10000 || i < 0);
				
				continue; // This returns the user to the accountMenu.
				
			} else if(s.equals("e")) {
				// Logout.
				doNotLogout = false; // This boolean variable is what keeps the accountMenu do while loop going.
				
				acc.logout();
				
			} else {
				System.out.println("Sorry, that is not a valid option.");
				
				continue;
			}
		} while(doNotLogout == true); // Change this later or you won't be able to leave the accountMenu;
	}
}
