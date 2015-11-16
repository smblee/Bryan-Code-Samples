package hw.hw2;

import java.util.Random;

public class Dice {
	private int numDice;

	public Dice() {
		numDice = 1;
	}

	public int getNumDice() {
		return numDice;
	}

	public void addDie() {
		numDice++;
	}

	public boolean rollDiceAndAcedOut() {
		for (int i = 0; i < numDice; i++) {
			Random rd = new Random();
			// randomize from 1 ~ 4
			int rand = 1 + rd.nextInt(4);
			if (rand == 1)
				return true;
		}
		return false;
	}

	public void resetNumDice() {
		numDice = 1;
	}
}
