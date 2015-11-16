package hw.hw5;

abstract class AnalystDecorator implements StockAnalyst {
	private StockAnalyst sa;
	private String reason; // put as a constructor because reasons may change dynamically
	
	public AnalystDecorator(StockAnalyst sa) {
		this.sa = sa;
	}
	
	public StockAnalyst getSA() {
		return sa;
	}
	
	public String reasons() {
		return getSA().reasons() + "\n" + getReason();
	}
	
	public void setReason(String reason) {
		this.reason = reason;
	}
		
	public String getReason() {
		return reason;
	}
	
	public String get(String key) {
		return getSA().get(key);
	}

}