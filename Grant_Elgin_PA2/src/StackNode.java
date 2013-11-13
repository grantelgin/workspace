/**
 * 
 * @author grantelgin StackNode includes the methods to create and read a
 *         stackNode object required to build a stackManager linkedList Stack.
 * 
 */

public class StackNode {
	private int column;
	private int row;
	private StackNode next;

	public StackNode() {
		setColumn(0);
		setRow(0);
		setNext(null);
	}

	public void setLocation(int column, int row) {
		setColumn(column);
		setRow(row);
		setNext(next);
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public StackNode getNext() {
		return next;
	}

	public void setNext(StackNode next) {
		this.next = next;
	}

}
