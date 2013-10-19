import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;


public class TelListManager implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private TelListItem head;
	private TelListItem tail;
	private int size;
	private String currentFileName; 

	public TelListManager() {
		head = tail = null;
		size = 0;
		setCurrentFileName("");
	}

	public void headAdd(TelListItem data) {

		TelListItem n = new TelListItem();
		n.setListItem(data);

		if (head == null) {
			// First node in list
			head = tail = n;
			return;
		}

		n.setNext(head);
		n.setPrev(null);

		head.setPrev(n);
		head = n;

		size++;
	}

	public boolean deleteTelListItem(TelListItem toDelete) {
		TelListItem current = head;
		TelListItem nextItem = new TelListItem();
		TelListItem nextNext = new TelListItem();
		if (size == 0)
			return false;

		boolean wasDeleted = false;
		
		//Are we deleting the only item in the list?
		if (toDelete.getName().equalsIgnoreCase(current.getName())) {
			if (current.getNext() == null) {
				head.setName(null);
				head.setEmail(null);
				head.setPhoneNumber(null);
				wasDeleted = true;
				
			}
			else {
				head.setListItem(current.getNext());
				current = null;
				wasDeleted = true;
				
			}
			
		}
		else {
		

		while (true) {
			//If end of list - stop
			if (current == null) {
				wasDeleted = false;
				break;
			}
			
			//Check to see if the next node is what were looking for
			nextItem.setListItem(current.getNext());
			if (nextItem != null) {
				if (toDelete.getName().equalsIgnoreCase(nextItem.getName())) {
					//found the right one, loop around the node
					if (nextItem.getNext() != null) {
					nextNext.setListItem(nextItem.getNext());
					current.setNext(nextNext);
					nextNext.setPrev(current);
					}
					else {
						current.setNext(null);
					}
					nextItem = null;
					wasDeleted = true;
					break;
				}
			}
			current = current.getNext();
		}
		}
		if (wasDeleted)
			size--;
		
		return wasDeleted;

	}

	public int getSize() {
		return size;
	}
	
	public boolean printList() {
		// print the entire list
		String rtn = "";
		TelListItem t = head;
		boolean result = true;
		if (t != null) {
		for (int x = 0; x <= size; x++) {
			rtn += "Name: " + t.getName() + "\nEmail: " + t.getEmail() + "\nPhone number: " + t.getPhoneNumber() + "\n";
			t = t.getNext();
		}
		
		System.out.println(rtn);
		}
		else {
			System.out.println("The current list is empty. Select a list to print or add items to the current list.\n");
			result = false;
		}
		
		return result;
	}
	
	public TelListItem searchByName (String name) {
		TelListItem result = new TelListItem();
		
		TelListItem current = head;
		// for multiple matches store matches in an array, ask user to refine further if necessary.
		// more work will have to be done on this loop. 
		for (int x = 0; x <= size; x++) {
			if (current.getName().equalsIgnoreCase(name))
				result = current;
			else 
				current = current.getNext();
		}
		
		return result;
	}
	
	public TelListItem searchByEmail (String email) {
		TelListItem result = new TelListItem();
		
		TelListItem current = head;
		// for multiple matches store matches in an array, ask user to refine further if necessary.
		// more work will have to be done on this loop. 
		for (int x = 0; x <= size; x++) {
			if (current.getEmail().equalsIgnoreCase(email))
				result = current;
			else 
				current = current.getNext();
		}
		
		return result;
	}
	

	public boolean writeOut(TelListManager list, String fileName) {
		boolean result = true;
		
		setCurrentFileName(fileName);
		
		File f = new File(getCurrentFileName());
		
		FileOutputStream os = null;
		
		try {
			os = new FileOutputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			oos.writeObject(list);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		try {
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			os.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

	}
	
	public void readBack() {
		
		File f = new File(getCurrentFileName());
		
		FileInputStream is = null;

		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			TelListManager list = (TelListManager) ois.readObject();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			is.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}

	public String getCurrentFileName() {
		if (currentFileName == "") {
			String currentTime = new SimpleDateFormat("yyyyMMddkkmmss").format(new Date());
			setCurrentFileName(currentTime);
		}
		
		return currentFileName;
	}

	public void setCurrentFileName(String currentFileName) {
		this.currentFileName = currentFileName;
	}
}
