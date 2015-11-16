package hw.hw3;

public class Customer {
	private int arrivalTime;
	private int serviceTime;
	private ItemDistribution id;
	private int item;
	
	
	public Customer(int t, ItemDistribution id) {
		this.id = id;
		arrivalTime = t;
		item = setItems();
		serviceTime = item + 6;
	}
	
	// call the item distribution method to get random items.
	public int setItems() {
		return id.howManyItems();
	}

	// higher the speed number, faster it is.
	public void elapseOneSecond(int speed) {
		serviceTime -= speed;
	}

	// instead of ==, changed to <= because the fast speed register could make it negative.
	public boolean isFinished() {
		return serviceTime <= 0;
	}

	public int arrivalTime() {
		return arrivalTime;
	}
}