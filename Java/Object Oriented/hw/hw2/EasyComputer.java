package hw.hw2;

public class EasyComputer extends ComputerPlayer {
	public EasyComputer() {
		super();
		setName("CPU" + getPlayerNum() + "(Easy)");
	}

	public int makeDecision(int currPot, int currDice) {
		return 0; // always bet
	}
}
