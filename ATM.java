/*
 * Sam Platt
 * CS 257
 * Written Jan 23, 2020
 */

import java.util.Scanner;

public class ATM {
	
	// constants
	public static final double WITHDRAW_FEE = 1.5;
	
	// non-constant class variables
	
	// instance data
	String s;
	int i;
	double d;
	long l;
	boolean doNotLogout = true;
	
	private static Scanner scan = new Scanner(System.in);
	
	// The class should have a single instance variable, which is the Account object that the ATM operates on.
	Bank bonk;
	String name;
	
	// The class should have a single constructor that takes as its argument an Account object that will be stored in the ATM object instance variable.
	ATM(Bank bonk, String name) {
		this.bonk = bonk;
		this.name = name;
	}
	
	public void mainMenu() {
		System.out.println("WELCOME TO THE MAIN MENU");
		
		do {
			// Prompt to decide what the action is:
			System.out.println("Please select one of the available options:\na\tCreate a new account\nb\tLogin to an existing account\nc\tExit");
			
			// Scan input
			s = scan.nextLine();
			
			if(s.equals("a")) { // Option a: Create a new Account
				
				// Have the user input their name.
				System.out.println("What is your name?");
				
				s = scan.nextLine();
				
				long thatsAnL = bonk.createAccount(s);
				
				// Invoke the appropriate method on your Bank to create a new Account for that user.
				if(thatsAnL != -1) {
					// Tell the user what their new account number is, so they can later use it to access their account.
					System.out.println("Your new account number is " + thatsAnL + ".");
					
				} else {
					// If you are unable to add another account for any reason, give the user an informative error message and do nothing.
					System.out.println("Account creation unsuccessful.");
				}
				
				
			} else if(s.equals("b")) { // Option b: Login to an existing Account
				/*
				 * Have the user input an integer PIN number and then call the login method on the account instance variable with the user-input PIN.
				 */
				
				Account tryLogin;
				
				do {
					// Have the user input their account number and attempt to retrieve that account from the bank.
					System.out.println("What is your account number?");
					s = scan.nextLine();
					l = Long.parseLong(s);
					
					tryLogin = bonk.getAccount(l);
					
					if(tryLogin == null) { // If the Account number is invalid, give the user an informative error message and do nothing.
						System.out.println("That is not a valid account number.");
						
						
						break;
					
					/*
					 * If the Account number is valid...
					 * Proceed with the Lab 3 login and account menu process on the indicated Account object,
					 * adjusted for the fact that the Account object is no longer an instance variable.
					 */
					} else {
				
						System.out.println("What is your PIN?");
						s = scan.nextLine();
						i = Integer.parseInt(s);
					 
						
						tryLogin.login(i);
					
						// Give an informative error message.
						if(tryLogin.getIsLoggedIn() == false) {
							System.out.println("Sorry, your PIN was incorrect.");
						}
					}
				} while(tryLogin.getIsLoggedIn() != true);
				
				// This is necessary because when a nonexistent account number is ended it will try to execute this code.
				if(tryLogin != null) {
					// Invoke the accountMenu method.
					doNotLogout = true;
					accountMenu(tryLogin);
				}
				
			} else if(!s.equals("c")) {
				// Invalid option message
				
				System.out.println("Sorry, that is not a valid option.");
			}
			
		// Option c: Exit	
		} while(!(s.equals("c"))); // input does not equal "exit".
	}
	
	public void accountMenu(Account account) {
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
						account.deposit(d); // Perform the deposit.
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
						if(account.getBalance() >= d + WITHDRAW_FEE) {
							// Check if the balance is at least the sum of the withdrawal amount and the service fee.
							
							account.withdraw(d, WITHDRAW_FEE);
							
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
				
				System.out.println(account.toString());
				
				System.out.println("You successfully viewed your account info.");
				
				continue; // This returns the user to the accountMenu.
				
			} else if(s.equals("d")) {
				// Change PIN.
				
				do {
					System.out.println("What would you like to change your PIN to?");
					
					s = scan.nextLine();
					i = Integer.parseInt(s);
					
					if(i < 10000 && i >= 0) {
						account.setPIN(i);
						
						System.out.println("You successfully changed your PIN.");
						
						i = 0;
						
					}
					
				} while(i > 10000 || i < 0);
				
				continue; // This returns the user to the accountMenu.
				
			} else if(s.equals("e")) {
				// Logout.
				doNotLogout = false; // This boolean variable is what keeps the accountMenu do while loop going.
				
				account.logout();
				
			} else {
				System.out.println("Sorry, that is not a valid option.");
				
				continue;
			}
		} while(doNotLogout == true); // Change this later or you won't be able to leave the accountMenu;
	}
}
