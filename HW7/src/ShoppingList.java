
import java.util.InputMismatchException;

/**
 * Aug 3, 2013
 * Grant Elgin
 * CS 232 HW6
 * 
 * ShoppingList gets a list of available items, has user input items they want to purchase, verifies the items are available,
 * verifies funds are available and has user set a priority for each item if the total exceeds available funds.  
 */

public class ShoppingList extends Lister {
	
	public ShoppingList()
	{
		head = null;
	}
	
	public static void main(String[] args) throws IntegerException {
		FileIO.write();
		ShoppingList sp = new ShoppingList();

		PrintOut.printInstructions();

		// show user the items that are available
		sp.showAvailableItems();

		// then get input from user to build shopping list
		sp.buildItemList();

	}
	
	/**
	Displays the data on the list
	*/
	public void showList()
	 {
		ListNode position = head;
		while (position != null)
		{
			System.out.println(position.getData());
			position = position.getLink();
		}
	 }
	 
	 public int length()
	 { 
		int count  = 0;
		ListNode position = head;
		while (position != null)
		{
			count++;
			position = position.getLink();
		}
		return count;
	 }
	 
	 public void insertFirstNode(Item addData)
	 {
		head = new ListNode(addData, head);
	 }
	 
	 public void deleteHeadNode()
	 {
		if (head != null)
			head = head.getLink();
		else
		{
			System.out.println("Deleting from an empty list.");
			System.exit(0);
		}
	 }
	 
	 public boolean onList(Item target)
	 {  
		return find(target) != null; 
	 }
	 
	 public ListNode find(Item target)
	 {
		boolean found = false; 
		ListNode position = head; 
		while ((position != null) && !found)
		{
			Item dataAtPosition = position.getData();
			if (dataAtPosition.equals(target))
				found = true;
			else 
				position = position.getLink();
		}
		return position;
	 }

	public void buildItemList() {
		Item anItem = new Item();
		String inputName;
		int qty;
		ShoppingList theLinkedList = new ShoppingList();
		// prompt user to input the 7 items they want to add to list
		for (int x = 0; x < 7; x++) {
			System.out.print("Enter an item name: ");
			inputName = keyboard.nextLine();
			boolean validItem = true;
			//
			// validate item name
			//anItem = Item.validateItem(inputName);
			anItem = FileIO.validateItemFromFile(inputName);
			if (anItem.getName().equalsIgnoreCase("no item")) {
				System.out.println("Sorry, that item is not available. Please re-enter an item from the list.");
				validItem = false;
			}
			else {
				//
				// check to see if item has already been added to list
				if (theLinkedList.onList(anItem)) {
					System.out.println("You have already added " + anItem.getName() + " to your list. Please select a different item.");
					validItem = false;
				}
				else {
					try {
						System.out.println("Qty: ");
						qty = keyboard.nextInt();
						anItem.setQty(qty);
						theLinkedList.insertFirstNode(anItem);
						
					}
					catch (InputMismatchException e) {
						validItem = false;
					}
				}

			}

			// if item is valid, add to list and continue for loop
			if (validItem) {
				
				//System.out.printf("item %1s: %-20s%10s $%6.2f\n", x + 1, al.get(x).getName(), "price:", al.get(x).getPrice());
				try {
					System.out.println("added:");
					System.out.printf("item %1s: %-20s%10s $%6.2f %20s%10.2f \n", x + 1, theLinkedList.find(anItem).getData().getName(), "unit price:", + theLinkedList.find(anItem).getData().getPrice(), "item total: $ ", calcTotalItemCost(theLinkedList.find(anItem).getData().getPrice(), theLinkedList.find(anItem).getData().getQty()));
					
					keyboard.nextLine();
					
				}
				catch (NullPointerException e) {
					System.out.println("Woops! Something went wrong!\nException: " + e.toString());
					
				}
			}
			// if item is not valid, repeat for current item
			else
				x--;
		}

		// calculate the total cost and display to user
		double listCost = calcTotalCost(theLinkedList);
		PrintOut.printItemTable(theLinkedList, listCost);
		System.out.println();

		// verify funds are available
		checkFunds(listCost, theLinkedList);
	}


}
