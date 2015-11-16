package hw.hw7;

import java.util.*;

public class Plus extends MyOperator{
	
	public Plus() {
		setOperator("+");
	}
	
	public int evaluate(Map<String,Integer> m) {
		int ans = 0;
		Iterator<ArithmeticExpression> it = iterator();
		while (it.hasNext()) {
			ans += it.next().evaluate(m);
		}
		return ans;
	}
	
	public int defaultInt() {
		return 0;
	}
}
