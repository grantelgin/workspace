/**
 * Aug 3, 2013
 * Grant Elgin
 * CS 232 HW5
 * 
 *  Lister contains all of the methods for creating a shopping list and using a FundsAccount to purchase a list of Items. 
 *  
 */
 
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class Lister extends BubbleSort  {
	private double totalCost;
	private String name;
	private static ListNode head;
	
	public Lister() {
		totalCost = -1;
		name = "no name";
	}
	
	public static Scanner keyboard = new Scanner( System.in );
	
	public void showAvailableItems() {
		// get available items from the Item class
		String[] availableItems = Item.listAvailableItems();
		// display each of the available items on a line
		for (int x = 0; x < 10; x++) {
			System.out.println(availableItems[x]);
		}
		
		System.out.println();	
	}
	
	public abstract void buildItemList();
	
	public static void checkFunds(double listCost, ShoppingList theLinkedList) {
		FundsAccount funds = new FundsAccount();
		double availFunds = 0;
		String whichAccount = "Which account would you like to use to purchase these materials?\n\nTo use the default funds account, type 'default' and press return.\nTo use a new account, type 'new':"; 
		System.out.println(whichAccount);
		String pickAccount = keyboard.next();
		boolean validAccount = false;
		
		if (pickAccount.equalsIgnoreCase("default")) {
			funds.setAvailableFunds(59.0);
			availFunds = funds.getAvailableFunds();
			funds.writeOutput();
			validAccount = true;
		}
		if (pickAccount.equalsIgnoreCase("new")) {
			funds = funds.createFundsAccount();
			availFunds = funds.getAvailableFunds();
			funds.writeOutput();
			validAccount = true;
		}
		if (pickAccount.equalsIgnoreCase("exit")) {
			System.exit(0);
		}
		
		if (validAccount) {
			// if the total cost exceeds the available funds, prompt the user to set the priority to buy the most important items first
			if (listCost > availFunds) {
				System.out.printf("Your total cost, $%6.2f, exceeds your account balance of $%6.2f. \nSet the priority for each item on your list. \nItems will be added to your shopping list in order of priority until an item exceeds your budget.\n", listCost, availFunds);
				setItemPriority(theLinkedList, funds);
			}
			else
				goShopping(theLinkedList, funds);
			}
		else {
			System.out.println("Woops! Please enter 'default' or 'new'.\n");
			checkFunds(listCost, theLinkedList);
		}
	
	}
	
	public static void checkFunds(double listCost, ArrayList<Item> al) {
		FundsAccount funds = new FundsAccount();
		double availFunds = 0;
		String whichAccount = "Which account would you like to use to purchase these materials?\n\nTo use the default funds account, type 'default' and press return.\nTo use a new account, type 'new':"; 
		System.out.println(whichAccount);
		String pickAccount = keyboard.next();
		boolean validAccount = false;
		
		if (pickAccount.equalsIgnoreCase("default")) {
			funds.setAvailableFunds(59.0);
			availFunds = funds.getAvailableFunds();
			funds.writeOutput();
			validAccount = true;
		}
		if (pickAccount.equalsIgnoreCase("new")) {
			funds = funds.createFundsAccount();
			availFunds = funds.getAvailableFunds();
			funds.writeOutput();
			validAccount = true;
		}
		if (pickAccount.equalsIgnoreCase("exit")) {
			System.exit(0);
		}
		
		if (validAccount) {
			// if the total cost exceeds the available funds, prompt the user to set the priority to buy the most important items first
			if (listCost > availFunds) {
				System.out.printf("Your total cost, $%6.2f, exceeds your account balance of $%6.2f. \nSet the priority for each item on your list. \nItems will be added to your shopping list in order of priority until an item exceeds your budget.\n", listCost, availFunds);
				setItemPriority(al, funds);
			}
			else
				goShopping(al, funds);
			}
		else {
			System.out.println("Woops! Please enter 'default' or 'new'.\n");
			checkFunds(listCost, al);
		}
	
	}
	
	public static void setItemPriority(ShoppingList theLinkedList, FundsAccount funds) {
		int priority;
		ListNode position = head;
		
		while (position != null)
		{
			Item dataAtPosition = position.getData();
			System.out.print("\nEnter the priority for " + dataAtPosition.getName() + ": ");
			try {
			priority = keyboard.nextInt();
			//
			// Validate item name
			dataAtPosition.setPriority(priority);
			System.out.printf("%10d%25s%25d%6.2f\n", 1, dataAtPosition.getName(), dataAtPosition.getPriority(), dataAtPosition.getPrice());
			}
			catch (InputMismatchException e){
				System.out.println("Woops! Please enter an integer to se the priority");
				keyboard.nextLine();
				//x--;
			}
			position = position.getLink();
		}	
			//sortBubble(al, funds);
		double totalCost = Lister.calcTotalCost(theLinkedList);
		ShoppingList.printItemTable(theLinkedList, totalCost);
		ShoppingList.goShopping(theLinkedList, funds);
	}
	public static void setItemPriority(ArrayList<Item> al, FundsAccount funds) {

		int priority;
		
		// prompt the user to set the priority for the items in their list
		for (int x = 0; x < 7; x++) {	
			System.out.print("\nEnter the priority for " + al.get(x).getName() + ": ");
			try {
			priority = keyboard.nextInt();
			//
			// Validate item name
			al.get(x).setPriority(priority);
			System.out.printf("%10d%25s%25d%6.2f\n", x, al.get(x).getName(), al.get(x).getPriority(), al.get(x).getPrice());
			}
			catch (InputMismatchException e){
				System.out.println("Woops! Please enter an integer to se the priority");
				keyboard.nextLine();
				x--;
			}
		}
			
			sortBubble(al, funds);		
	}

	public static void goShopping(ShoppingList theLinkedList, FundsAccount funds) {
		double availFunds = funds.getAvailableFunds();
		String unPurchasedItems = "";
		ListNode position = head;
		ArrayList<Item> unPurchased = new ArrayList<Item>();
		ShoppingList.printColumnHeading("Item #", "Priority", "Name", "Unit Price", "Qty", "Item Total");
		System.out.println("Purchased items:\n ");
		 
		while (position != null)
		{
			Item dataAtPosition = position.getData();
			if (calcTotalItemCost(dataAtPosition.getPrice(), dataAtPosition.getQty()) <= availFunds) {
				ShoppingList.printRow(1, dataAtPosition.getPriority(), dataAtPosition.getName(), dataAtPosition.getPrice(), dataAtPosition.getQty(), Lister.calcTotalItemCost(dataAtPosition.getPrice(), dataAtPosition.getQty()));
				availFunds = (availFunds - calcTotalItemCost(dataAtPosition.getPrice(), dataAtPosition.getQty()));
			}
			else {
				unPurchased.add(dataAtPosition);
			}
			position = position.getLink();
		}
		// if there are items in the list that were not purchased, display them to the user. 
				System.out.println("Items remaining to be purchased:\n " + unPurchasedItems);
				for (int u = 0; u < unPurchased.size(); u++) {
					ShoppingList.printRow(u + 1, unPurchased.get(u).getPriority(), unPurchased.get(u).getName(), unPurchased.get(u).getPrice(), unPurchased.get(u).getQty(), Lister.calcTotalItemCost(unPurchased.get(u).getPrice(), unPurchased.get(u).getQty()));
				}
				
				System.out.printf("\nRemaining account balance: $%10.2f", availFunds);
			
	}
	
	public static void goShopping(ArrayList<Item> al, FundsAccount funds) {
		
		double availFunds = funds.getAvailableFunds();
		String unPurchasedItems = "";
		ArrayList<Item> unPurchased = new ArrayList<Item>();
		// loop through the items in order of priority. Minimize the amount of available funds left.
		ShoppingList.printColumnHeading("Item #", "Priority", "Name", "Unit Price", "Qty", "Item Total");
		System.out.println("Purchased items:\n ");
		for (int x = 0; x < 7; x++) {
			if (calcTotalItemCost(al.get(x).getPrice(), al.get(x).getQty()) <= availFunds) {
				ShoppingList.printRow(x + 1, al.get(x).getPriority(), al.get(x).getName(), al.get(x).getPrice(), al.get(x).getQty(), Lister.calcTotalItemCost(al.get(x).getPrice(), al.get(x).getQty()));
				availFunds = (availFunds - calcTotalItemCost(al.get(x).getPrice(), al.get(x).getQty()));
			}
			else {
				unPurchased.add(al.get(x));
			}
		}
		// if there are items in the list that were not purchased, display them to the user. 
		System.out.println("Items remaining to be purchased:\n " + unPurchasedItems);
		for (int u = 0; u < unPurchased.size(); u++) {
			ShoppingList.printRow(u + 1, unPurchased.get(u).getPriority(), unPurchased.get(u).getName(), unPurchased.get(u).getPrice(), unPurchased.get(u).getQty(), Lister.calcTotalItemCost(unPurchased.get(u).getPrice(), unPurchased.get(u).getQty()));
		}
		
		System.out.printf("\nRemaining account balance: $%10.2f", availFunds);
	
	}
	public static double calcTotalCost(ShoppingList theLinkedList) {
		double totalCost = 0;
		ListNode position = head; 
		while (position != null)
		{
			Item dataAtPosition = position.getData();
			totalCost = totalCost + (dataAtPosition.getPrice() * dataAtPosition.getQty());
			position = position.getLink();
		}
		
		return totalCost;
	}
	
	public static double calcTotalCost(ArrayList<Item> al) {
		double totalCost = 0;
		for (int x = 0; x < 7; x++) {
			totalCost = totalCost + (al.get(x).getPrice() * al.get(x).getQty()); 
		}
		
		return totalCost;
		
	}

	// Item[] methods	
	public static void setItemPriority(Item[] listItem, FundsAccount funds) {
		Item[] sortedListItem = new Item[7];
		String inputName;
		System.out.println();
		// prompt the user to set the priority for the items in their list
		for (int x = 0; x < 7; x++) {	
			System.out.print("Enter the item with priority " + (x + 1) + ": ");
			inputName = keyboard.nextLine();
			boolean validItem = false;
			//
			// Validate item name
			sortedListItem[x] = Item.validateItem(inputName);
			if (sortedListItem[x].getName().equalsIgnoreCase("no item")) {
				System.out.println("That item was not on your shopping list. Please re-enter item.");
				x--;
			}
			else {
				//
				// verify item is on the list and set its priority
				for (int y = 0; y < 7; y++) {
					if (listItem[y].getName().equalsIgnoreCase(inputName)) {
						listItem[y].setPriority(x);
						System.out.println("added " + listItem[y].getName());
						sortedListItem[x] =  listItem[y];
						validItem = true;
					}
				}
				if (!validItem) {
					System.out.println("That item was not on your shopping list. Please re-enter item.");
					x--;
				}
					
			}
					
		}
		
		//goShopping(sortedListItem, funds);
	}
	
	public static void goShopping(Item[] list, FundsAccount funds) {
		
		double availFunds = funds.getAvailableFunds();
		String unPurchasedItems = "";
		// loop through the items in order of priority. Minimize the amount of available funds left.
		for (int x = 0; x < 7; x++) {
			if (list[x].getPrice() <= availFunds) {
				System.out.printf("Item name: %20s: Item price: $%6.2f Remaining balance: $%6.2f\n", list[x].getName(), list[x].getPrice(), (availFunds - list[x].getPrice()));
				availFunds = (availFunds - list[x].getPrice());
			}
			else
				unPurchasedItems = unPurchasedItems + (list[x].getName() + "\n");
		}
		// if there are items in the list that were not purchased, display them to the user. 
		System.out.println("Items remaining to be purchased:\n " + unPurchasedItems);
		
	}	
	public static double calcTotalCost(Item[] list) {
		double totalCost = 0;
		for (int x = 0; x < 7; x++) {
			totalCost = totalCost + list[x].getPrice(); 
		}
		
		return totalCost;
		
	}
	
	

	public boolean equals(Lister otherList) {
		return (this.name.equalsIgnoreCase(otherList.name));
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public static double calcTotalItemCost(double unitPrice, int qty) {
		return (unitPrice * qty);
	}
}
