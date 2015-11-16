package hw.hw5;

public class Bearish implements StockAnalyst{
	private StockInfo si;
	
	public Bearish(StockInfo si) {
		this.si = si;
	}

	public float confidenceLevel() {
		return 0.4f;
	}

	public String reasons() {
		return "I am negative.";
	}

	public String get(String key) {
		return si.get(key);
	}
	
}
