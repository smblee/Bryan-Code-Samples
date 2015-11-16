package hw.hw3;

public class Bimodal implements ItemDistribution {
	private int distSize;

	public Bimodal(int ds) {
		this.distSize = ds;
	}

	public int howManyItems() {
		int lowerLimit = distSize / 4; // from 1
		int highLimit = 3 * distSize / 4; // up to distSize

		int lowerRand = (int) Math.floor(Math.random() * lowerLimit) + 1;
		int highRand = (int) Math.floor(Math.random() * (distSize - highLimit + 1)) + highLimit;

		
		double choice = Math.random();

		if (choice >= 0.5) {
			return lowerRand;
		} else {
			return highRand;
		}

	}

	
//	 public static void main(String args[]) {
//		 Bimodal b = new Bimodal(11);
//		 int i = 0;
//		 while (i < 100)
//		 {
//			 System.out.println(b.howManyItems()); i++; 
//		 }
//		 
//		 
//	 }
}