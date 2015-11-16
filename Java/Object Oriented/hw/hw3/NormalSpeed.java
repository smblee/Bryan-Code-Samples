package hw.hw3;

public class NormalSpeed implements Cashier {
	public boolean elapseOneSecondAndIsCustomerFinished(Customer currentCust, int currentTime){
		currentCust.elapseOneSecond(1); //decrease by 1 second
		return currentCust.isFinished();
	}
}
