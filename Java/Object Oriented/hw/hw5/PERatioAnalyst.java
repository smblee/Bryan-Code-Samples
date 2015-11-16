package hw.hw5;

public class PERatioAnalyst extends AnalystDecorator{
	
	public PERatioAnalyst(StockAnalyst sa) {
		super(sa);
	}
	public float confidenceLevel() {
		float PERatio = calculatePriceEarningRatio();
		if (PERatio == 12) {
			setReason("Price earning seems average.");
			return (getSA().confidenceLevel() + 0.5f) / 2;
		}
		else if (PERatio > 12) {
			setReason("Price earning does not seem promising.");
			return (getSA().confidenceLevel() + 0.3f) / 2;
		}
		setReason("Price earning seems very promising!");
		return (getSA().confidenceLevel() + 0.7f) / 2;
	}
	
	public float calculatePriceEarningRatio() {
		return Float.parseFloat( get("shareprice") ) / Float.parseFloat( get("earnings") );
	}	
}
