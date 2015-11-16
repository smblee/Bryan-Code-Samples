package hw.hw5;

public class Bullish implements StockAnalyst{
	private StockInfo si;

	
	public Bullish(StockInfo si) {
		this.si = si;
	}

	public float confidenceLevel() {
		return 0.6f;
	}

	public String reasons() {
		return "I am a firm believer that this stock will be good.";
	}
	
	public String get(String key) {
		return si.get(key);
	}
}
