package hw.hw2;

public class Pot {
	private int potMoney = 0;

	public void addToPot(int amt) {
		potMoney += amt;
	}

	public int getPotMoney() {
		return potMoney;
	}

	public void resetPotMoney() {
		potMoney = 0;
	}
}