package hw.hw3;

public class Uniform implements ItemDistribution {
	private int distSize;
	
	public Uniform(int ds) {
		this.distSize=ds;
	}
	
	public int howManyItems() {
		int n = (int) (Math.random() * distSize);
		return n;
	}
	
	
}
