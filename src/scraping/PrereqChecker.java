package scraping;

import java.util.ArrayList;
import java.util.Scanner;

import schedule.*;

public class PrereqChecker {
	public ArrayList<String> iHaveTaken;
	
	public PrereqChecker() {
		this.iHaveTaken = new ArrayList<String>();
	}
	
	public void readRecords() {
		System.out.println("Please input your previous class codes. ");
		Scanner sc = new Scanner(System.in);
		for (String input = sc.nextLine();input.isEmpty() == false;input = sc.nextLine()) {
			iHaveTaken.add(input);
		}
		sc.close();
	}
	
	public void printRecords() {
		for (int i = 0; i<iHaveTaken.size(); i++) {
			System.out.println(iHaveTaken.get(i));
		}
	}

	public String codeAlternate(String code) {
		String alternate = code;
		if (Character.isWhitespace(code.charAt(2))) {
			
		}
		else if (Character.isAlphabetic(code.charAt(1)) && Character.isDigit(code.charAt(2))) {
			alternate = alternate.substring(0, 2) + " " + alternate.substring(2);
		}
		
		else if (Character.isDigit(code.charAt(3))) {
			alternate = alternate.substring(0, 3) + " " + alternate.substring(3);
		}
		else if (Character.isWhitespace(code.charAt(3))) {

		}
		else if (Character.isAlphabetic(code.charAt(3))) {
			if (Character.isWhitespace(code.charAt(4))) {
				
			}
			else if (Character.isDigit(code.charAt(4))) {
				alternate = alternate.substring(0, 4) + " " + alternate.substring(4);
			}
		}
		return alternate;
	}
	
	
	public boolean prereqMet(Course c) {
		boolean doI = false;
		for (int i = 0; i<this.iHaveTaken.size(); i++) {
			if (c.doYouMeet(this.iHaveTaken.get(i)) || c.doYouMeet(this.codeAlternate(this.iHaveTaken.get(i)))) {
				doI = true;
			}
		}
		return doI;
	}
	
	public boolean historyMatcher(Course c) {
		boolean didI = false;
		for (int i = 0; i<this.iHaveTaken.size(); i++) {
			if (c.didITake(this.iHaveTaken.get(i)) || c.didITake(this.codeAlternate(this.iHaveTaken.get(i)))) {
				didI = true;
			}
		}
		return didI;
	}
}
