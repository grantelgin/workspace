import java.util.ArrayList;
import java.util.InputMismatchException;

/**
 * Aug 3, 2013
 * Grant Elgin
 * CS 232 HW5
 * 
 * ShoppingList gets a list of available items, has user input items they want to purchase, verifies the items are available,
 * verifies funds are available and has user set a priority for each item if the total exceeds available funds.  
 */

public class ShoppingList extends Lister {

private static ListNode head;
	
	public ShoppingList()
	{
		head = null;
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
	 
	 private ListNode find(Item target)
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
	 

	
	public static void main(String[] args) {

		ShoppingList sp = new ShoppingList();

		printInstructions();

		// show user the items that are available
		sp.showAvailableItems();

		// then get input from user to build shopping list
		sp.buildItemList();


	}

	public static void printColumnHeading(String desc1, String desc2, String desc3, String desc4, String desc5, String desc6) {
		System.out.println("----------------------------------------------------------------------------------------------------------");
		System.out.printf("%-10s%-10s%-25s%22s%10s%25s%n", desc1, desc2, desc3, desc4, desc5, desc6);
		System.out.println("----------------------------------------------------------------------------------------------------------");
	}

	public static void printRow(int desc1, int p, String desc2, double d, int q, double f) {
		System.out.printf("%-10d%-10d%-38s$ %8.2f%10s          $%13.2f%n", desc1, p, desc2, d, q, f);
	}

	public static void printItemTable(ShoppingList theLinkedList, double totalCost) {
		double totalItemCost;
		printColumnHeading("Item #", "Priority", "Name", "Unit Price", "Qty", "Item Total");
		ListNode position = head;
		
		while (position != null) {
			Item dataAtPosition = position.getData();
			totalItemCost = Lister.calcTotalItemCost(dataAtPosition.getPrice(), dataAtPosition.getQty());
			printRow(1, dataAtPosition.getPriority(), dataAtPosition.getName(), dataAtPosition.getPrice(), dataAtPosition.getQty(), totalItemCost);
			position = position.getLink();
		}
		printTableTotal(totalCost);
	}
	public static void printItemTable(ArrayList<Item> al, double totalCost) {
		double totalItemCost;
		printColumnHeading("Item #", "Priority", "Name", "Unit Price", "Qty", "Item Total");
		for (int x = 0; x < al.size(); x++) {
			totalItemCost = Lister.calcTotalItemCost(al.get(x).getPrice(), al.get(x).getQty());
			printRow(x + 1, al.get(x).getPriority(), al.get(x).getName(), al.get(x).getPrice(), al.get(x).getQty(), totalItemCost);
		}
		printTableTotal(totalCost);
	}

	public static void printTableTotal(double total) {
		System.out.printf("\n%91s $ %6.2f\n", "TOTAL:", total);

	}

	public static void printInstructions () {
		System.out.println("Construction project shopping list\nv 1.2\n");
		System.out.println("To start building your shopping list, select an item from the list below.\nTyping the name of the item will add it to your list and display the current price.");
		System.out.println("Type 'exit' to close the program.");
	}

	public void buildItemList() {
		Item anItem = new Item();
		String inputName;
		int qty;
		//ArrayList<Item> al = new ArrayList<Item>(20);
		ShoppingList theLinkedList = new ShoppingList();
		// prompt user to input the 7 items they want to add to list
		for (int x = 0; x < 7; x++) {
			System.out.print("Enter an item name: ");
			inputName = keyboard.nextLine();
			boolean validItem = true;
			//
			// validate item name
			anItem = Item.validateItem(inputName);

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
						//al.add(anItem);
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
					System.out.printf("item %1s: %-20s%10s $%6.2f\n", x + 1, theLinkedList.find(anItem).getData().getName(), "price:", + theLinkedList.find(anItem).getData().getPrice());
					
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
		printItemTable(theLinkedList, listCost);
		System.out.println();

		// verify funds are available
		checkFunds(listCost, theLinkedList);
	}


}
