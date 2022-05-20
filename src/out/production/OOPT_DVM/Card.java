package dvmProject;

import java.util.*;

public class Card {

	public int balance;

	private String[] cardInfo;

	//cardinfo[0] == CardNum
	//cardinfo[1] == Vaild unitl
	//cardinfo[2] == CVC
	//cardInfo[3] == PW

	public Card(String[] cardInfo, int balance) {
		this.cardInfo = cardInfo;
		this.balance = balance;
	}

	public int getBalance() {
		return this.balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
}