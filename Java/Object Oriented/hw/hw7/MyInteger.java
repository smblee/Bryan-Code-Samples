package hw.hw7;

import java.util.*;

public class MyInteger extends MyTerm {
	int val;
	
	public MyInteger(int v) {
		val = v;
	}

	public String toString() {
		return Integer.toString(val);
	}
	
	public int evaluate(Map<String,Integer> m) {
		return val;
	}
	
	public boolean isInteger() {
		return true;
	}	
	
	public boolean isVariable() {
		return false;
	}
}
