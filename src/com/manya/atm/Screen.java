//Represent the screen of the ATM
package com.manya.atm;

public class Screen {
	//display a msg without carriage return
	public void displayMessage(String message){
		System.out.println(message);
	}
	//display a msg with a crriage return
	public void displayMessageLine(String message){
		System.out.println(message);
	}
	public void displayDollarAmount(double amount){
		System.out.printf("$%,.2f", amount);
	}

}
