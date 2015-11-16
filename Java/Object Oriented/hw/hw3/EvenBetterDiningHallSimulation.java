package hw.hw3;

import javax.swing.JOptionPane;

public class EvenBetterDiningHallSimulation {
	private static final int SIMULATION_TIME = 10000;  // A simulation is for 10,000 seconds.

	public static void main(String[] args) {
		String ans1 = JOptionPane.showInputDialog(null, "How many normal registers?");
		String ans2 = JOptionPane.showInputDialog(null, "How many fast registers?");
		String ans3 = JOptionPane.showInputDialog(null, "Which distribution type? \n 'b' for Bimodal, 'u' for uniform");
		String ans4 = JOptionPane.showInputDialog(null, "Distribution Size?");
		
		int normal = Integer.parseInt(ans1);
		int fast = Integer.parseInt(ans2);
		String distType = ans3;
		int distSize = Integer.parseInt(ans4);
		
		ItemDistribution id;
		if (distType.equals("u"))
			id = new Uniform(distSize);
		else
			id = new Bimodal(distSize);
		
		DiningHall hall = new DiningHall(normal, fast, id);
		
		for (int t=0; t<SIMULATION_TIME; t++) 
			hall.elapseOneSecond(t);

		hall.printStatistics();
	}
}
