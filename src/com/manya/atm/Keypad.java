//Represent the keypad of the ATM
package com.manya.atm;

import java.util.Scanner;

public class Keypad {
	//to read the data from the command line
	private Scanner input;
	public Keypad(){
		input = new Scanner(System.in);
	}
	public int getInput(){
		return input.nextInt();
	}
	
	
}
