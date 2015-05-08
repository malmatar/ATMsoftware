//Represent BalanceInquiry
package com.manya.atm;

public class BalanceInquiry extends Transaction {

	public BalanceInquiry(int accountNumber, Screen screen,
			BankDatabase bankDatabase) {
		super(accountNumber, screen, bankDatabase);
	}

	@Override
	public void execute() {
		//get reference to database and screen
		BankDatabase bankDatabase = getBankDatabase();
		Screen screen = getScreen();
		
		double availableBalance = bankDatabase.getAvailableBalance(getAccountNumber());
		double totalBalance = bankDatabase.getTotalBalance(getAccountNumber());
		
		//display the balance info on the screen
		screen.displayMessageLine("\nBalance Informations");
		screen.displayMessage(" - Available balance: ");
		screen.displayDollarAmount(availableBalance);
		screen.displayMessage("\n -Total balance: ");
		screen.displayDollarAmount(totalBalance);
		screen.displayMessage(" ");		

	}

}
