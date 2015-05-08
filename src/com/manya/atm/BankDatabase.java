//Represent the bank account database
package com.manya.atm;

public class BankDatabase {
	private Account[] accounts;
	public BankDatabase(){
		accounts = new Account[2];
		accounts[0] = new Account(12345, 54321, 1000.0, 1200.0);
		accounts[1] = new Account(98765, 56789, 200.0, 200.0);		
	}
	//retrive Account object containing specified account number
	private Account getAccount(int accountNumber){
		for(Account currentAccount : accounts){
			if(currentAccount.getAccountNumber()==accountNumber)
				return currentAccount;
		}
		return null;
	}	
	//determine whether user specific account number and PIN match 
	//those of an account in the database
	public boolean authenticateUser(int userAccountNumber, int userPIN){
		Account userAccount = getAccount(userAccountNumber);
		if(userAccount!=null)
			return userAccount.validPIN(userPIN);
		else
			return false;
	}
	public double getAvailableBalance(int userAccountNumber){
		return getAccount(userAccountNumber).getAvailableBalance();
	}
	public double getTotalBalance(int userAccountNumber){
		return getAccount(userAccountNumber).getTotalBalance();
	}
	public void credit(int userAccountNumber, double amount){
		getAccount(userAccountNumber).credit(amount);
	}
	public void debit(int userAccountNumber, double amount){
		getAccount(userAccountNumber).debit(amount);
	}
}
