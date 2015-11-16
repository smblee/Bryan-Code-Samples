package hw.hw1;

public class CashRegister {
	// statistics about the two cash registers
	private static int[] customersServed = new int[2];
	private static int[] totalWaitTimes = new int[2]; 
	
	public static void main(String[] args) {	
		for (int r=0; r<=1; r++) {
			customersServed[r] = 0;
			totalWaitTimes[r]  = 0;
		}
	}
	
	public int getCustomersServed(int reg)
	{
		return customersServed[reg];
	}
	
	public int getAverageWaitTime(int reg)
	{
		return (totalWaitTimes[reg] / customersServed[reg]);
	}

	public void addToCustomersServed(int reg)
	{
		customersServed[reg]++;
	}
	
	public void addToTotalWaitTimes(int reg, int currentTime, int arrivalTime)
	{
		totalWaitTimes[reg] += currentTime - arrivalTime;
	}
}
