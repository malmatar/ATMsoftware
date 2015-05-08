//Represent the cash dispenser of the ATM
package com.manya.atm;

public class CashDispenser {
	//The default initial number of bills in the cash dispenser
	private final static int INITIAL_COUNT = 500;
	private int count;//number of $20 bills remaining
	
	public CashDispenser(){
		count = INITIAL_COUNT;
	}
	public void dispenseCash(int amount){
		int billsRequired = amount/20;//number of $20 bills required
		count -= billsRequired;
	}
	public boolean isSufficientCashAvailable(int amount){
		int billsRequired = amount/20;
		if(count >= billsRequired)
			return true;
		else
			return false;
	}
}
