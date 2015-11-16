package hw.hw1;

public class BetterDiningHallSimulation {
	private static final int SIMULATION_TIME = 10000;  // A simulation is for 10,000 seconds.
	private static final int CUST_ARRIVAL_PCT = 19;    // There is a 19% chance a customer arrives each second.
	private static CashRegister cashRegisters = new CashRegister();
	private static Customer customer = new Customer();
	
	public static void main(String[] args) {

		// Then perform the simulation for the specified number of seconds.
		for (int t=0; t<SIMULATION_TIME; t++) {
			if (aCustomerArrives()) {
				// The new customer goes into the smaller line.
				int chosenRegister = customer.chooseRegister(); 
				customer.addToArrivalTimes(chosenRegister,t);
				customer.addToServiceTimes(chosenRegister);
			}
			
			for (int r=0; r<=1; r++)
				elapseOneSecond(r, t);  // Simulate each register for one second.
		}

		// Print out the statistics.
		for (int r=0; r<=1; r++) {
			System.out.println("Register " + r);
			System.out.println("\tNumber of arrivals = " + cashRegisters.getCustomersServed(r));
			System.out.println("\tAverage wait time = " + cashRegisters.getAverageWaitTime(r));
		}
	}

	private static boolean aCustomerArrives() {
		int n = (int) (Math.random() * 100);  // an integer between 0 and 99
		return n < CUST_ARRIVAL_PCT;
	}
	
	private static void elapseOneSecond(int reg, int currentTime) {
		// If the list is empty, there are no customers to process.
		if ( customer.getArrivalTimesSize(reg) == 0)
			return;

		// Otherwise, the first customer in line gets processed.
		int timeLeft = customer.getServiceTimeLeft(reg);
		if (timeLeft > 0) { 
			customer.setServiceTimes(reg, timeLeft);
		}
		else { // We are done with this customer.
			// First update the register's statistics.
			cashRegisters.addToCustomersServed(reg);
			cashRegisters.addToTotalWaitTimes(reg, currentTime, customer.getArrivalTimes(reg));

			// Then remove the customer
			customer.removeArrivalTimes(reg);
			customer.removeServiceTimes(reg);
		}
	}
}