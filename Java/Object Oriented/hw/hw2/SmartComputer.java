package hw.hw2;

public class SmartComputer extends ComputerPlayer {
	public SmartComputer() {
		super();
		setName("CPU" + getPlayerNum() + "(Smart)");
	}

	public int makeDecision(int currPot, int currDice) {
		if (currPot >= 3)
			return 1;

		return 0;
	}
}
