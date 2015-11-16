package hw.hw7;

import java.util.*;

abstract class MyOperator implements ArithmeticExpression {
	private List<ArithmeticExpression> components = new ArrayList<ArithmeticExpression>();
	private String operator;
	
	public String toString() {
		String s = "(";
		Iterator<ArithmeticExpression> it = iterator();
		if (it.hasNext())
		{
			s = s + it.next().toString(); // first element
			while(it.hasNext()) { // rest elements
				s = s + getOperator() + it.next().toString();				
			}
		} else {
			return Integer.toString(defaultInt());
		}
		return s + ")";
	}

	public void addTerm(ArithmeticExpression ae) {
		components.add(ae);
	}
	
	public Iterator<ArithmeticExpression> iterator() {
		return components.iterator();
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}
	
	public boolean isInteger() {
		return false;
	}
	
	public boolean isVariable() {
		return false;
	}
	
	abstract int defaultInt();
	
}	
