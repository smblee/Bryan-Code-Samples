package hw.hw5;

public class SegmentAnalyst extends AnalystDecorator{

	public SegmentAnalyst(StockAnalyst sa) {
		super(sa);
	}

	public float confidenceLevel() {
		String ms = get("marketsegment");
		if (ms.equals("technology")) {
			setReason("Technology is the BEST!! GET IT RIGHT NOW!");
			return (getSA().confidenceLevel() + 0.8f) / 2;
		}
			
		else {
			setReason("This market segment is NOT recommended.");
			return (getSA().confidenceLevel() + 0.2f) / 2;
		}
	}
	
}
