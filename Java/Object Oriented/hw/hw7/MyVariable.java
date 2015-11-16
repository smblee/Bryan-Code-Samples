package hw.hw7;

import java.util.*;

public class MyVariable extends MyTerm {
	String varName;
	
	public MyVariable(String vn) {
		varName = vn;
	}
	
	public String toString() {
		return varName;
	}
	
	public int evaluate(Map<String,Integer> m) {
		if ( m.containsKey(toString()) )
			return m.get(toString());
		else
			throw new UnsupportedOperationException();
	}
	
	public boolean isInteger() {
		return false;
	}
	
	public boolean isVariable() {
		return true;
	}

}
