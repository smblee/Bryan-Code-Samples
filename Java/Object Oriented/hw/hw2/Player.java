package hw.hw2;

public abstract class Player {
	private static int nextPlayerNum = 0;
	private int chipsOwned, playerNum;
	private String name;

	public Player() {
		playerNum = nextPlayerNum;
		nextPlayerNum++;
		chipsOwned = 0;
	}

	public void setName(String n) {
		name = n;
	}

	public int getPlayerNum() {
		return playerNum;
	}

	public int getChipsOwned() {
		return chipsOwned;
	}

	public void addChips(int amt) {
		chipsOwned += amt;
	}

	abstract int makeDecision(int currPot, int currDice);

	public boolean playOneTurn(Dice dice, Pot pot) {
		// player adds chips equivalent to the number of dices into the pot.
		// player then rolls the dice.
		pot.addToPot(dice.getNumDice());
		boolean acedOut = dice.rollDiceAndAcedOut();
		return acedOut;
	}

	public void collectThePot(Dice dice, Pot pot) {
		chipsOwned += pot.getPotMoney();
	}

	public String getName() {
		return name;
	}

}
