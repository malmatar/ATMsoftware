//
package com.manya.atm;

public class Deposit extends Transaction {
	
	private double amount;//amount to deposit
	private Keypad keypad;//reference to keypad
	private DepositSlot depositSlot;//reference to deposit slot
	private boolean envelopeReceived;
	private final static int CANCELED = 0;//constant for cancle option

	public Deposit(int userAccountNumber, Screen atmScreen, BankDatabase atmBankDatabase, Keypad atmKeypad, DepositSlot atmDepositSlot ) {
		super(userAccountNumber, atmScreen, atmBankDatabase);
		keypad = atmKeypad;
		depositSlot = atmDepositSlot;
	}

	@Override
	public void execute() {
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		amount = promtForDepositAmount();//get deposit amount from user
		
		//check whether user entered a deposit amount or canceled
		if(amount != CANCELED){
			screen.displayMessage("\nPlease insert a deposit evnelope containing ");
			screen.displayDollarAmount(amount);
			screen.displayMessageLine(".");
			
			boolean evelopeReceived = depositSlot.isEnvelopeReceived();
			if(envelopeReceived){
				screen.displayMessageLine("\nYour envelope has been " +
						"received.\nNOTE: The money just deposited will not " +
						"be available until we verify the amount of any " +
						"enclosed cash and your checks clear.");
				//credit account to reflect the deposit
				bankDatabase.credit(getAccountNumber(), amount);
			}else{
				screen.displayMessageLine("\nYou did not insert an "+
						"envlope, so the ATM cancelled your transaction.");				
			}		
		}else{
			screen.displayMessageLine("\nCanceling transaction...");
		}		
	}
	private double promtForDepositAmount(){
		Screen screen = getScreen();
		screen.displayMessage("\nPlease enter a deposit amount in " + 
		"CENTS (OR 0 TO CANCEL): ");
		int input = keypad.getInput();
		if(input==CANCELED)
			return CANCELED;
		else{
			return (double)input/100;
		}
	}

}
