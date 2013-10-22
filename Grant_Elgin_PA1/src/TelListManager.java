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

/**
 *  TelListManager manages the current list and contains methods to add, delete, search by name or email, 
 *  save to a file and restore from a file 
 *  @author grantelgin
 *  */

public class TelListManager implements Serializable {

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
		// add a TelListItem to the beginning of the list. 
		TelListItem n = new TelListItem();
		n.setListItem(data);

		if (head == null) {
			// First node in list
			head = tail = n;
			size++;
			return;
		}

		n.setNext(head);
		n.setPrev(null);

		head.setPrev(n);
		head = n;

		size++;
	}

	public boolean deleteTelListItem(TelListItem toDelete) {
		// remove an item from the current list.

		TelListItem current = head;
		TelListItem nextItem = new TelListItem();
		TelListItem nextNext = new TelListItem();
		// return if there are no items to delete
		if (size == 0)
			return false;

		boolean wasDeleted = false;

		//Are we deleting the head item? Is it the only item in the list?
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

				//Check to see if the next node is what we're looking for
				nextItem.setListItem(current.getNext());
				if (nextItem != null) {
					if (toDelete.getName().equalsIgnoreCase(nextItem.getName())) {
						//found the right one, set adjacent nodes
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
		TelListItem t = head;
		boolean result = true;
		System.out.println("================================================================================");
		System.out.printf("%-8s%-25s%-30s%-20s\n", "index", "Name", "Email address", "Phone number");
		System.out.println("================================================================================\n");
		if (t != null) {
			for (int x = 0; x < size; x++) {
				System.out.printf("%-8s%-25s%-30s%-20s\n", x+1, t.getName(), t.getEmail(), t.getPhoneNumber());
				t = t.getNext();
			}
		}
		else {
			System.out.println("The current list is empty. Select a list to print or add items to the current list.\n");
			result = false;
		}
		System.out.println();

		return result;
	}

	public TelListManager mergeCurrentList(TelListManager listToUpdate) {
		// Start with the head of the current list, add to listToUpdate, loop through currentList, add each item to listToUpdate. 
		TelListItem t = head;

		if (t != null) {
			for (int x = 0; x < size; x++) {
				listToUpdate.headAdd(t);
				t = t.getNext();
			}
		}

		return listToUpdate;
	}

	public TelListItem searchByName (String name) {
		TelListItem result = new TelListItem();
		TelListItem current = head;
		// for multiple matches store matches in an array, ask user to refine further if necessary.
		// TODO add result to array for multiple matches, return array.  
		for (int x = 0; x < size; x++) {
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
		// TODO add result to array for multiple matches, return array.
		for (int x = 0; x < size; x++) {
			if (current.getEmail().equalsIgnoreCase(email))
				result = current;
			else 
				current = current.getNext();
		}

		return result;
	}


	public boolean writeOut(TelListManager list, String fileName) {
		// Write current list to a new file. WARNING! If an existing file is named, that file will be overwritten.
		// user is prompted to use restore instead of writeOut if they want to update an existing file. 
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

	public TelListManager readBack(String fileName) {
		// replaces the currentList with a stored file. User is prompted to add currentList items if currentList.getSize() > 0.
		File f = new File(fileName);
		TelListManager list = new TelListManager();
		FileInputStream is = null;

		try {
			is = new FileInputStream(f);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
			return list;
		}

		ObjectInputStream ois = null;
		try {
			ois = new ObjectInputStream(is);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			list = (TelListManager) ois.readObject();
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
		
		return list;
	}

	public String getCurrentFileName() {
		// if a currentFile has not been specified, currentTime is suggested to the user. 
		// User is prompted to change name or use currentTime. 
		
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
