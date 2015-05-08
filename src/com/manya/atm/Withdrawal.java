package com.manya.atm;

public class Withdrawal extends Transaction {
	private int amount;// amount to withdraw
	private Keypad keypad;// reference to keypad
	private CashDispenser cashDispenser;// reference to cash dispenser
	
	private final static int CANCELED = 6;
	
	
	public Withdrawal(int userAccountNumber, Screen atmScreen,
			BankDatabase atmBankDatabase, Keypad atmKeypad, CashDispenser atmCashDispenser) {
		super(userAccountNumber, atmScreen, atmBankDatabase);
		keypad = atmKeypad;
		cashDispenser = atmCashDispenser;
	}

	@Override
	public void execute() {
		boolean cashDispensed = false;//cash was not dispensed yet
		double availableBalance;//amount available for withdrawal
		
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		do{
			amount = displayMenuOfAmount();
			if(amount != CANCELED){
				availableBalance= bankDatabase.getAvailableBalance(getAccountNumber());
				if(amount<=availableBalance){
					if(cashDispenser.isSufficientCashAvailable(amount)){
						bankDatabase.debit(getAccountNumber(), amount);
						cashDispenser.dispenseCash(amount);
						cashDispensed = true;
						screen.displayMessageLine("\nYour cash has been"+
						" dispensed. please take your cash now. ");						
					}else
						screen.displayMessageLine("\nInsufficient cash available in the ATM." +
								"\n\nPlease choose a smaller amount.");
				}else{
					screen.displayMessageLine("\nInsufficient funds in your account."+
							"\n\nPlease choose a smaller amount.");
				}
			}else{
				screen.displayMessageLine("\nCanceling transaction...");
				return;
			}
		}while(!cashDispensed);
	}
	//display a menu of withdrawal amounts and the option 
	//return the chosen amount or 0 if the user chooses to cancel
	private int displayMenuOfAmount() {
		int userChoise = 0;
		Screen screen = getScreen();
		
		//arrays of amounts to correspond to menu numbers
		int[] amounts = {0, 20, 40, 60, 100, 200};
		
		while(userChoise == 0){
			screen.displayMessageLine("\nWithdrawal Menu: ");
			screen.displayMessageLine("1 - $20");
			screen.displayMessageLine("2 - $40");
			screen.displayMessageLine("3 - $60");
			screen.displayMessageLine("4 - $100");
			screen.displayMessageLine("5 - $200");
			screen.displayMessageLine("\nChoose a withdrawal amount: ");
			int input = keypad.getInput();
			switch(input){
				case 1:
				case 2:
				case 3:
				case 4:
				case 5:
					userChoise = amounts[input];
					break;
				default:screen.displayMessageLine("\nInvalid selection. Try again.");
			}
		}
		return userChoise;
	}	
}
