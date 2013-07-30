/**
 * July 25, 2013
 * Grant Elgin
 * CS 232 HW4
 * 
 *  Lister contains all of the methods for creating a shopping list and using a FundsAccount to purchase a list of Items. 
 *  
 */
 
import java.util.InputMismatchException;
import java.util.Scanner;


public abstract class Lister {
	private double totalCost;
	private String name;
	

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
	
	public static void checkFunds(double listCost, Item[] listItem) {
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
		
	if (validAccount) {
		// if the total cost exceeds the available funds, prompt the user to set the priority to buy the most important items first
		if (listCost > availFunds) {
			System.out.printf("Your total cost, $%6.2f, exceeds your account balance of $%6.2f. \nSet the priority for each item on your list. \nItems will be added to your shopping list in order of priority until an item exceeds your budget.\n", listCost, availFunds);
			//setItemPriority(listItem, funds);
			setBubble(listItem, funds);
		}
		else
			goShopping(listItem, funds);
	}
		else {
			System.out.println("Woops! Please enter 'default' or 'new'.\n");
			checkFunds(listCost, listItem);
		}
	}
	
	public abstract void buildItemList();
	
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
		
		goShopping(sortedListItem, funds);
	}
	
	public static void setBubble(Item[] listItem, FundsAccount funds) {
		int[] sortedPriority = new int[7];
		//Item[] sortedListItem = new Item[7];
		int inputInt;
		System.out.println();
		// prompt the user to set the priority for the items in their list
		for (int x = 0; x < 7; x++) {	
			System.out.print("Enter the priority for " + listItem[x].getName() + ": ");
			try {
				inputInt = keyboard.nextInt();
				sortedPriority[x] = inputInt;
				System.out.println(listItem[x].getName() + " priority: " + sortedPriority[x]);
				listItem[x].setPriority(sortedPriority[x]);
			}
			catch (InputMismatchException e) {
				System.out.println("Please enter an integer.");
				inputInt = keyboard.nextInt();
			}
					
		}
		sortBubble(listItem, funds);
		goShopping(listItem, funds);
	}
	
	/* For debugging - print out priority for each item in the list. 
	 * public static void printPriority(Item[] listItem) {
		for (int x = 0; x < listItem.length; x++) {
			System.out.print(listItem[x].getPriority() + " ");
		}
		System.out.println();
	}*/
	
	public static void sortBubble(Item[] listItem, FundsAccount funds) {
		int x;
		boolean flag = true;
		Item temp;
		//printPriority(listItem);
		while (flag) {
			flag = false;
			for ( x = 0; x < listItem.length - 1; x++) {
				if (listItem[x].getPriority() < listItem[x+1].getPriority()) {
					temp = listItem[x];
					listItem[x] = listItem[x+1];
					listItem[x+1] = temp;
					flag = true;
				}
			}
			//printPriority(listItem);
			
			
		}
	
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
}
