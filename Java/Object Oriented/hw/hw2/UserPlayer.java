package hw.hw2;

import javax.swing.JOptionPane;

public class UserPlayer extends Player {
	public UserPlayer(String n) {
		super();
		setName(n);
	}

	public int makeDecision(int currPot, int currDice) {
		String msg = "It's your turn, " + getName() + "!\nCurrently in your pocket: " + getChipsOwned()
				+ "\nCurrently in the pot: " + currPot + "\nDices to roll: " + currDice;
		String[] buttons = { "Roll", "Collect the Pot", "View Stats", "Quit" };
		int rc = JOptionPane.showOptionDialog(null, msg, "In Game", JOptionPane.INFORMATION_MESSAGE, 3, null, buttons,
				buttons[0]);
		return rc;
	}
}
