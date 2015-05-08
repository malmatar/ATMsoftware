//Represent a bank account
package com.manya.atm;

public class Account {
	private int accountNumber;
	private int pin;
	private double availableBalance;//funds available for withdrawal 
	private double totalBalance;//funds available + pending deposits
	
	public Account(int accountNumber, int pin, double availableBalance,
			double totalBalance) {
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.availableBalance = availableBalance;
		this.totalBalance = totalBalance;
	}
	
	public boolean validPIN(int userPIN){
		if(userPIN == pin)
			return true;
		else
			return false;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public double getAvailableBalance() {
		return availableBalance;
	}

	public double getTotalBalance() {
		return totalBalance;
	}
	public void credit(double amount){
		totalBalance += amount;
	}
	public void debit(double amount){
		availableBalance -= amount;
		totalBalance -= amount;		
	}	

}
