package hw.hw7;

import java.util.Map;
import java.util.function.Consumer;

public interface ArithmeticExpression extends Iterable<ArithmeticExpression> {
	public String toString();
	public int evaluate(Map<String, Integer> m);
	public boolean isInteger();
	public boolean isVariable();
	public default void traverse(Consumer<ArithmeticExpression> f) {
		if (isVariable())
			f.accept(this);
		forEach(mc -> mc.traverse(f));
	}
}
