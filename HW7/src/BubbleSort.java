import java.util.ArrayList;
/**
 * Grant Elgin
 * CS232
 * HW 6 
 * bubbleSort rearranges an ArrayList according to the value of 'getPriority'. After the items are sorted, they are put in to a linkedList
 * and returnde to the calling method.  
 */

public class BubbleSort extends ShoppingList {
	
	public static ShoppingList bubbleSort(ArrayList<Item> al) {
		ShoppingList sortedList = new ShoppingList();
		int x;
		boolean flag = true;
		Item temp;
		// sort arraylist items
		while (flag) {
			flag = false;
			for ( x = 0; x < al.size() - 1; x++) {
				if (al.get(x).getPriority() < al.get(x+1).getPriority()) {
					temp = al.get(x);
					al.set(x, al.get(x+1));
					al.set(x+1, temp);
					flag = true;
				}
			}
			
		}
		// add sorted arraylist items to linkedList 
		for (int i = 0; i < al.size(); i++) {
			sortedList.insertFirstNode(al.get(i));
		}
		return sortedList;
		
	}
}
