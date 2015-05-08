package com.manya.atm;

public class ATM {
	private Screen screen;//ATM's screen
	private Keypad keypad;//ATMs keypad
	private CashDispenser cashDispenser;//ATM's cash dispenser 
	private DepositSlot depositSlot;//ATM's deposit slot
	private BankDatabase bankDatabase;//account information database
	
	private boolean userAuthenticated;//Whether user is authenticated
	private int currentAccountNumber;//Current user's account number
	// constants corresponding to main menue options.
	private static final int BALANCE_INQUIRY = 1;
	private static final int WITHDRAWAL = 2;
	private static final int DEPOSIT = 3;
	private static final int EXIT = 4;
	
	//no argument constructor
	public ATM(){
		userAuthenticated = false; // user is not authenticated to start 
		currentAccountNumber = 0; // no current account number to start
		screen = new Screen(); 
		keypad = new Keypad(); 
		cashDispenser = new CashDispenser(); 
		depositSlot = new DepositSlot(); 
		bankDatabase = new BankDatabase(); //create acct info database
	}
	//start ATM
	public void run(){
		//welcome and authenticate user; perform transactions
		while(true){
			//while user is not authenticated
			while(!userAuthenticated){
				screen.displayMessageLine("\nWelcome!");
				authenticateUser();//authenticate user
			}
			performTransaction();//user is authenticated
			userAuthenticated = false;//reset before the next session
			currentAccountNumber = 0;
			screen.displayMessageLine("\nThank you! Goodbye!");
		}
	}
	private void authenticateUser(){
		screen.displayMessage("\nPlease enter your account number: ");
		int accountNumber = keypad.getInput();
		screen.displayMessage ("\nEnter your PIN: ");
		int pin = keypad.getInput();
		//set userAuthenticated to boolean value returned by database.
		userAuthenticated = bankDatabase.authenticateUser(accountNumber, pin);
		//check whether authentication succeeded
		if(userAuthenticated){
			currentAccountNumber = accountNumber;
		}
		else
			screen.displayMessageLine("Invalid account number or PIN. Please try again.");
	}
	//display the main menu and perform transaction
	private void performTransaction(){
		//Local variable to store transaction currently being processed		
		Transaction currentTransaction = null;
		boolean userExited = false;//user has not chosen to exit system
		while(!userExited){
			int mainMenuSelection = displayMainMenu();
			switch(mainMenuSelection){
				case BALANCE_INQUIRY:
				case WITHDRAWAL:
				case DEPOSIT:
					//initialize as new object of chosen type
					currentTransaction = createTransaction(mainMenuSelection);//execute transaction
					currentTransaction.execute();				
				break;
				case EXIT://user chose to terminate session
					screen.displayMessageLine("\nExiting the system ...");
					userExited = true;
					break;
				default://user didn't enter an integer from 1-4
					screen.displayMessageLine("\nYou didn't enter a valid selection. Try again.");
					break;
			}				
		}	
	}
	//display the main menu and return input selection
	private int displayMainMenu(){
		screen.displayMessageLine("\nMain menu: ");
		screen.displayMessageLine("1 - view my balance");
		screen.displayMessageLine("2 - withdraw cash");
		screen.displayMessageLine("3 - Deposit funds");
		screen.displayMessageLine("4 - Exit");
		return keypad.getInput();		
	}
	//return object of specified Transaction subclass
	private Transaction createTransaction(int type){
		Transaction temp = null;// temporaray transaction variable
		//determine which type of transaction to create 
		switch(type){
			case BALANCE_INQUIRY://create new balance inquiry transaction
				temp = new BalanceInquiry(currentAccountNumber, screen, bankDatabase);
				break;
			case WITHDRAWAL: //create new withdrawal transaction
				temp = new Withdrawal(currentAccountNumber, screen, bankDatabase, keypad, cashDispenser);
				break;
			case DEPOSIT: //create new deposit transaction
				temp = new Deposit(currentAccountNumber, screen, bankDatabase, keypad, depositSlot);
				break;
		}
		return temp;
	}

}
