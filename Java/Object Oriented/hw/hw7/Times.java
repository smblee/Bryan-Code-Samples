package hw.hw7;

import java.util.*;

public class Times extends MyOperator {
	
	public Times() {
		setOperator("*");
	}

	public int evaluate(Map<String,Integer> m) {
		int ans = 1;
		Iterator<ArithmeticExpression> it = iterator();
		while (it.hasNext()) {
			ans *= it.next().evaluate(m);
		}
		return ans;
	}
	public int defaultInt() {
		return 1;
	}
	
}
