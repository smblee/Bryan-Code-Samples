package hw.hw2;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class HW2DiceGame {
	private static List<Player> players = new ArrayList<Player>();
	private static Pot pot = new Pot();
	private static Dice dice = new Dice();

	private static boolean gameOver = false;
	private static int currentTurn = 0;

	public static void main(String args[]) {
		String msg = "Welcome to the game! \n " + "What is your name?";
		String name = JOptionPane.showInputDialog(msg);

		msg = "How many other players would you like to play with?";
		String answer = JOptionPane.showInputDialog(msg);
		int numOfCPU = Integer.parseInt(answer);

		// create the user and set to index 0
		Player user = new UserPlayer(name);
		players.add(user);

		// set difficulty of computers
		msg = "Select the difficulty of the computers?\n" + "1=easy   2=shy   3 = smart";
		String diff = JOptionPane.showInputDialog(msg);
		int difficulty = Integer.parseInt(diff);
		Player cpu;

		// create the computers
		for (int i = 0; i < numOfCPU; i++) {
			if (difficulty == 1) {
				cpu = new EasyComputer();
			} else if (difficulty == 2) {
				cpu = new ShyComputer();
			} else {
				cpu = new SmartComputer();
			}
			players.add(cpu);
		}

		// begin the game
		while (!gameOver) {
			Player currPlayer = players.get(currentTurn);

			// winning condition
			if (currPlayer.getChipsOwned() >= 50) {
				gameOver = true;
				JOptionPane.showMessageDialog(null, currPlayer.getName() + " WON!");
				System.exit(0);
			}
			
			// if first dice
			else if (isFirstTurn()) {
				processOneTurn();
			}
			// get player's decision to bet or not, and process that decision.
			else {
				int choice = getDecision();
				processCommand(choice);
			}
		}
	}

	private static void processCommand(int cmd) {
		if (cmd == 0) {
			processOneTurn();
		} else if (cmd == 1) {
			endTurn();
		} else if (cmd == 2) {
			printStats();
		} else {
			JOptionPane.showMessageDialog(null, "Good bye!");
			System.exit(0);
		}
	}

	private static int getDecision() {
		Player currPlayer = players.get(currentTurn);
		return currPlayer.makeDecision(pot.getPotMoney(), dice.getNumDice());
	}

	private static boolean processOneTurn() {
		boolean acedOut = players.get(currentTurn).playOneTurn(dice, pot);
		if (acedOut) {
			System.out.println(
					"Player " + players.get(currentTurn).getName() + " acedOut after " + dice.getNumDice() + " rolls ");
			dice.resetNumDice();
			printStats();
			nextTurn();
		} else {
			dice.addDie();
		}
		return acedOut;
	}

	private static void endTurn() {
		System.out.println("Player " + players.get(currentTurn).getName() + " stopped after " + (dice.getNumDice() - 1)
				+ " rolls and won " + pot.getPotMoney() + " chips");
		players.get(currentTurn).collectThePot(dice, pot);
		pot.resetPotMoney();
		dice.resetNumDice();
		printStats();
		nextTurn();
	}

	private static void nextTurn() {
		if (currentTurn == players.size() - 1)
			resetTurn();
		else
			currentTurn++;
	}

	private static void resetTurn() {
		currentTurn = 0;
	}

	private static boolean isFirstTurn() {
		return dice.getNumDice() == 1;
	}

	private static void printStats() {
		System.out.print("Chip count: ");
		for (int i = 0; i < players.size(); i++) {
			System.out.print(players.get(i).getName() + ": " + players.get(i).getChipsOwned() + "  ");
		}
		System.out.println();
		System.out.println("Pot contains: " + pot.getPotMoney() + "\n");

	}
}
