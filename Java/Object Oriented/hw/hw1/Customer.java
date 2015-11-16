package hw.hw1;

import java.util.LinkedList;
import java.util.List;

public class Customer {
	// info about customers waiting at the two cash registers
	private static List<Integer>[] arrivalTimes = (List<Integer>[]) new List[2];
	private static List<Integer>[] serviceTimes = (List<Integer>[]) new List[2];
	
	public Customer() {
		for (int r=0; r<=1; r++) {
			arrivalTimes[r] = new LinkedList<Integer>();
			serviceTimes[r] = new LinkedList<Integer>();
		}
	}
	

	
	public int chooseRegister() {
		return arrivalTimes[0].size() < arrivalTimes[1].size() ? 0 : 1;
	}
	
	public void addToArrivalTimes(int chosenRegister, int t) {
		arrivalTimes[chosenRegister].add(t);
	}
	
	public void addToServiceTimes(int chosenRegister) {
		serviceTimes[chosenRegister].add(howManyItems() + 5);
	}

	public int getArrivalTimes(int reg) {
		return arrivalTimes[reg].get(0);
	}

	
	public int getArrivalTimesSize(int reg) {
		return arrivalTimes[reg].size();
	}
	
	public int getServiceTimeLeft(int reg) {
		return serviceTimes[reg].get(0)-1;
	}
	
	public void setServiceTimes(int reg, int timeLeft) {
		serviceTimes[reg].set(0, timeLeft);
	}
	
	public void removeArrivalTimes(int reg) {
		arrivalTimes[reg].remove(0);
	}
	
	public void removeServiceTimes(int reg) {
		serviceTimes[reg].remove(0);
	}
	
	private static int howManyItems() {
		int n = (int) (Math.random() * 10);
		return n + 1;
	}
	
	
	
}
