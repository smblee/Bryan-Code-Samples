package hw.hw3;

import java.util.ArrayList;
import java.util.List;

public class DiningHall {
	private static final int CUST_ARRIVAL_PCT = 19;
	// changed arrays to arrayList
	private List<CashRegister> cashRegisters = new ArrayList<CashRegister>();
	
	public DiningHall(int normal, int fast, ItemDistribution id) {
		for (int nr = 0; nr < normal; nr++)
		{
			// make new cashier every time a register is created
			Cashier newCashier = new NormalSpeed();
			CashRegister newNormRegister = new CashRegister(id, newCashier);
			cashRegisters.add(newNormRegister);
		}
		for (int fr = 0; fr < fast; fr++)
		{
			Cashier newCashier = new FastSpeed();
			CashRegister newFastRegister = new CashRegister(id, newCashier);
			cashRegisters.add(newFastRegister);
		}
	}

	public void elapseOneSecond(int t) {
		if (aCustomerArrives()) {
			// The new customer goes into the smaller line.
			// iterate through all cash registers and compare sizes.
			// If current chosenRegister is greater, then replace it.
			CashRegister chosenRegister = cashRegisters.get(0);
			for (CashRegister cr : cashRegisters) {
				chosenRegister = chosenRegister.size() > cr.size() ? cr : chosenRegister;
			}
			chosenRegister.addCustomer(t);
		}
		for (CashRegister cr : cashRegisters){
			cr.elapseOneSecond(t);// Simulate each register for one second.
		}
	}

	public void printStatistics() {
		for (CashRegister cr : cashRegisters) {
			System.out.println("Register " + cashRegisters.indexOf(cr));
			System.out.println("\tNumber of arrivals = " + cr.customersServed());
			System.out.println("\tAverage wait time = " + cr.avgWaitTime());
		}
	}

	private boolean aCustomerArrives() {
		int n = (int) (Math.random() * 100); // an integer between 0 and 99
		return n < CUST_ARRIVAL_PCT;
	}
}