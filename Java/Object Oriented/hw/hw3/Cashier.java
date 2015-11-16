package hw.hw3;

public interface Cashier {
	// elapseOneSecond will return if the customer is finished
	public boolean elapseOneSecondAndIsCustomerFinished(Customer currentCust, int currentTime);
}
