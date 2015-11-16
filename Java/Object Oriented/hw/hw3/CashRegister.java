package hw.hw3;

import java.util.*;

public class CashRegister {
	private List<Customer> custs = new LinkedList<Customer>();
	private int numServed = 0;
	private int totalWaitTime = 0;
	private Customer currentCust = null;
	private Cashier cashier;
	private ItemDistribution id;
	
	public CashRegister(ItemDistribution id, Cashier cashier) {
		this.id = id;
		this.cashier = cashier;
	}

	public void addCustomer(int t) {
		Customer c = new Customer(t, id);
		custs.add(c);
		if (currentCust == null)
			currentCust = c;
	}

	public void elapseOneSecond(int currentTime) {
		// Return if there is no current customer.
		if (currentCust == null)
			return;
		
		// let the cashier take care of the currentCustomer, and return if the customer is finished or not.
		boolean customerFinished = cashier.elapseOneSecondAndIsCustomerFinished(currentCust, currentTime);
		
		if (customerFinished) {
			numServed++;
			totalWaitTime += currentTime - currentCust.arrivalTime();

			// Remove the finished customer and make the next customer current.
			custs.remove(0);
			currentCust = custs.size() > 0 ? custs.get(0) : null;
		}
	}

	public int size() {
		return custs.size();
	}

	public int customersServed() {
		return numServed;
	}

	// if there are lots of registers, sometimes some registers don't serve anyone.
	// in order to prevent divide by 0 error, I added a checking method.
	public int avgWaitTime() {
		if (numServed == 0) {
			return 0;
		}
		return totalWaitTime / numServed;
	}

	protected void setRegisterStrategy(Cashier cashier, ItemDistribution id) {
		this.cashier = cashier;
		this.id = id;
	}

}
