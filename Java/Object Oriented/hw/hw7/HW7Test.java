package hw.hw7;

import java.util.*;

public class HW7Test {
	private static int max = 0;
	private static int intCount = 0;
	public static void main(String[] args) {
		MyOperator plus1 = new Plus(); // TOP operation
		MyOperator times1 = new Times();
		MyOperator plus2 = new Plus();
		MyOperator times2 = new Times();
		plus1.addTerm(new MyInteger(3));
		plus1.addTerm(new MyInteger(4));
		plus1.addTerm(new MyVariable("y"));
		plus1.addTerm(times1);
			times1.addTerm(new MyInteger(5));
			times1.addTerm(new MyInteger(6));
			times1.addTerm(plus2);
				plus2.addTerm(new MyInteger(4));
				plus2.addTerm(new MyInteger(7));
				plus2.addTerm(times2);
					times2.addTerm(new MyVariable("x"));
					times2.addTerm(new MyInteger(-1));
		plus1.addTerm(new MyInteger(10));		
		plus1.addTerm(new MyVariable("y"));
		Map<String,Integer> m = new HashMap<String,Integer>();
			m.put("x", 13);
			m.put("y", 2);
			m.put("z", 12);
		System.out.println("infix representation: " + plus1); // ignored toString() because Java recognizes this method automatically.
		System.out.println("evaluation: " + plus1.evaluate(m));
		iterateInteger(plus1); // external method
		System.out.println("intCount, max: " + Integer.toString(intCount) + ", "
											 + Integer.toString(max));
		System.out.println("Variables:: "); 
		plus1.traverse(e -> System.out.println(e)); // internal method
		
	}
	
	public static void iterateInteger(ArithmeticExpression ae) {
		if( ae.isInteger() ) {
			intCount++;
			max = Math.max(max, ae.evaluate(null));
		}
		else {
			Iterator<ArithmeticExpression> it = ae.iterator();
			while(it.hasNext()) {
				ArithmeticExpression next = it.next();
				iterateInteger(next);
			}
		}
	}
}