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


public abstract class Lister  {
	private double totalCost;
	private String name;
	protected static ListNode head;
	protected static ListNode next;
	
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
	
	public abstract void buildItemList() throws IntegerException;
	
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

	
	public static void setItemPriority(ShoppingList theLinkedList, FundsAccount funds) {
		int priority;
		ListNode position = head;
		
		while (position != null)
		{
			Item dataAtPosition = position.getData();
			System.out.print("\nEnter the priority for " + theLinkedList.find(dataAtPosition).getData().getName() + ": ");
			try {
			priority = keyboard.nextInt();
			//
			// Validate item name

			theLinkedList.find(dataAtPosition).getData().setPriority(priority);
			System.out.printf("%10d%25s%25d%6.2f\n", 1, theLinkedList.find(dataAtPosition).getData().getName(), theLinkedList.find(dataAtPosition).getData().getPriority(), theLinkedList.find(dataAtPosition).getData().getPrice());
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
			if (calcTotalItemCost(theLinkedList.find(dataAtPosition).getData().getPrice(), theLinkedList.find(dataAtPosition).getData().getQty()) <= availFunds) {
				ShoppingList.printRow(1, theLinkedList.find(dataAtPosition).getData().getPriority(), theLinkedList.find(dataAtPosition).getData().getName(), theLinkedList.find(dataAtPosition).getData().getPrice(), theLinkedList.find(dataAtPosition).getData().getQty(), Lister.calcTotalItemCost(theLinkedList.find(dataAtPosition).getData().getPrice(), theLinkedList.find(dataAtPosition).getData().getQty()));
				availFunds = (availFunds - calcTotalItemCost(theLinkedList.find(dataAtPosition).getData().getPrice(), theLinkedList.find(dataAtPosition).getData().getQty()));
			}
			else {
				unPurchased.add(theLinkedList.find(dataAtPosition).getData());
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
	
	
	public static double calcTotalCost(ShoppingList theLinkedList) {
		double totalCost = 0;
		ListNode position = head; 
		while (position != null)
		{
			Item dataAtPosition = position.getData();
			totalCost = totalCost + (theLinkedList.find(dataAtPosition).getData().getPrice() * theLinkedList.find(dataAtPosition).getData().getQty());
			position = position.getLink();
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
