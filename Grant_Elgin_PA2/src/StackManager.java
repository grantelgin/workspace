
public class StackManager {
private int count;
private StackNode top;

public StackManager() {
	setTop(null);
	setCount(0);
}

public boolean isEmpty() {
	return (top == null);
}

public StackNode peek() {
	if (isEmpty()) 
		return null;
	
	return top;
}

public StackNode pop() {
	if (isEmpty()) 
		return null;
	
	StackNode rtn = top;
	top = top.getNext();
	count--;
	
	return rtn;
}

public boolean push(StackNode move) {
	StackNode n = new StackNode();
	n.setColumn(move.getColumn());
	n.setRow(move.getRow());
	
	
	n.setNext(top);
	top = n;
	count++;
	return true;
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
