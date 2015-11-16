package hw.hw3;

public class FastSpeed implements Cashier{
	public boolean elapseOneSecondAndIsCustomerFinished(Customer currentCust, int currentTime){
		currentCust.elapseOneSecond(2); //decrease by 2
		return currentCust.isFinished();
	}
}
