import java.math.BigInteger;
import java.util.ArrayList;

public class P11Monkey {
	public static final long MULTIPLY = 1;
	public static final long ADD = 2;
	private ArrayList<Long> items;
	private long testDivisor;
	private long throwTrue;
	private long throwFalse;
	private long operation;
	private long worryLevel;
	private long inspections;
	private long lcm;

	public P11Monkey(long operation, long worryLevel, long testDivisor, long throwTrue, long throwFalse, long... items) {
		this.items = new ArrayList<Long>();
		for (int i = 0; i < items.length; i++) {
			this.items.add(items[i]);
		}
		this.testDivisor = testDivisor;
		this.throwTrue = throwTrue;
		this.throwFalse = throwFalse;
		this.operation = operation;
		this.worryLevel = worryLevel;
		this.inspections = 0;
		this.lcm = 0;
	}

	public long getTestDivisor() {
		return this.testDivisor;
	}
	
	public void setLCM(long lcm) {
		this.lcm = lcm;
	}
	
	
	public void business2(P11Monkey[] monkey) {
		for (int i = 0; i < this.items.size(); i++) {
			long item = this.items.get(i);
			if (this.operation == P11Monkey.ADD) {
				
				if (this.worryLevel == -1) {
					item = item + item;
				} else {
					item = item + this.worryLevel;
				}
			} else if (this.operation == P11Monkey.MULTIPLY) {
				
				if (this.worryLevel == -1) {
					item = item * item;
				} else {
					item = item * this.worryLevel;
				}
			}
			
			item = item % this.lcm;
			
			if((item % this.testDivisor) == 0) {
				monkey[(int) this.throwTrue].items.add(item);
			} else {
				monkey[(int) this.throwFalse].items.add(item);
			}

			
			this.inspections++;
		}
		
		this.items.clear();
	}
	
	
	public long getInspections() {
		return this.inspections;
	}

}
