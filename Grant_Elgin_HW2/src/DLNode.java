

public class DLNode {

	private String Data;
	
	private DLNode next;
	private DLNode prev;
	
	@Override
	public String toString() {
		return "[Data=" + Data + "] ";
	}
	public String getData() {
		return Data;
	}
	public void setData(String data) {
		Data = data;
	}
	public DLNode getNext() {
		return next;
	}
	public void setNext(DLNode next) {
		this.next = next;
	}
	public DLNode getPrev() {
		return prev;
	}
	public void setPrev(DLNode prev) {
		this.prev = prev;
	}
}
