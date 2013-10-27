

public class StackManager {
	private StackNode top = null;
	private int count;
	
	public StackManager() {
		setTop(null);
		setCount(0);
	}
	
	public boolean isEmpty() {
		return (top == null);
	}
	
	public double peek() {
		if (isEmpty()) 
			return 0;
		
		return top.getOperand();
	}
	
	public double pop() {
		if (isEmpty()) 
			return 0;
		
		double rtn = top.getOperand();
		top = top.getNext();
		count--;
		
		return rtn;
	}
	
	public boolean push(double x) {
		StackNode n = new StackNode();
		n.setOperand(x);
		
		n.setNext(top);
		top = n;
		count++;
		return true;
	}
	
	public int size() {
		return count;
	}
	
	public boolean isFull() {
		return false;
	}
	
	public String toString() {
		String rtn = "";
		
		if (isEmpty()) {
			return "<Empty>";
		}
		
		StackNode n = top;
		rtn += "Top -> ";
		for (int i = 0; i < count; i++) {
			rtn += n.getOperand() + "\n       ";
			n = n.getNext();
		}
		
		return rtn;
	}
	
	public String getStack() {
		String rtn = "";
		
		if (isEmpty()) {
			return "<Empty>";
		}
		
		StackNode n = top;
		rtn += "Top -> ";
		for (int i = 0; i < count; i++) {
			rtn += n.getOperand() + "\n       ";
			n = n.getNext();
		}
		
		return rtn;
	}

	public StackNode getTop() {
		return top;
	}

	public void setTop(StackNode top) {
		this.top = top;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}
}

