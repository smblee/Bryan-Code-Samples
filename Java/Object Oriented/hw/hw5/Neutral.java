package hw.hw5;

public class Neutral implements StockAnalyst{

	private StockInfo si;
	
	public Neutral(StockInfo si) {
		this.si = si;
	}
	
	public float confidenceLevel() {
		return 0.5f;
	}

	public String reasons() {
		return "This stock is both good and bad.";
	}
	
	public String get(String key) {
		return si.get(key);
	}
	
}
