package hw.hw2;

public class ShyComputer extends ComputerPlayer {
	public ShyComputer() {
		super();
		setName("CPU" + getPlayerNum() + "(Shy)");
	}

	public int makeDecision(int currPot, int currDice) {
		return 1; // Always stop betting
	}
}
