package hw.hw7;

import java.util.ArrayList;
import java.util.Iterator;

abstract class MyTerm implements ArithmeticExpression {

	public Iterator<ArithmeticExpression> iterator() {
		// Return an iterator that has no elements.
		return new ArrayList<ArithmeticExpression>().iterator();
	}

}