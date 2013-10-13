

public class DLList {
	
	private DLNode head;
	private DLNode tail;
	
	public DLList() {
		head = tail = null;
	}
	
	public void headAdd(String data) {
		
		DLNode n = new DLNode();
		n.setData(data);
		
		if (head == null) {
			// First node in list
			head = tail = n;
			return;
		}

		n.setNext(head);
		n.setPrev(null);
		
		head.setPrev(n);
		head = n;
	}
	
	// Complete a tail add for this class
	// and an add after node class
	
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
	public void tailAdd(String data) {
		DLNode n = new DLNode();
		n.setData(data);
		
		if (head == null) {
			head = tail = n;
			return;
		}
		
		n.setPrev(tail);
		n.setNext(null);

		tail.setNext(n);		
		tail = n;
		
	}
	
	public void afterAdd(DLNode n, String s) {
		// to find correct insert point, loop through list until n.getData() matches an item in the list object.
		// start looping at beginning of list object.
		DLNode before = head;

		while (before.getData().toString() != n.getData().toString()) {
			before = before.getNext();
			if (before == null)
				return; // couldn't find it
		}
		// now that before is set with correct node from list, build new node to be inserted
		DLNode a = new DLNode();
		a.setData(s);
		
		if (head == null) {
			head = tail = a;
			return;
		}
		a.setNext(before.getNext());
		a.setPrev(before);
		
		before.setNext(a);
		
	}

	@Override
	public String toString() {
		String rtn = "";
		
		DLNode n = head;
		while (n != null) {
			rtn += n + " ";
			n = n.getNext();
		}
		return rtn;
	} 
}