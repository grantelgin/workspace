

public class StackNode {
	private double operand;
	private StackNode next;
	
	public StackNode() {
		setOperand(0);
		setNext(null);
	}

	public double getOperand() {
		return operand;
	}

	public void setOperand(double operand) {
		this.operand = operand;
	}

	public StackNode getNext() {
		return next;
	}

	public void setNext(StackNode next) {
		this.next = next;
	}
	
	

}

