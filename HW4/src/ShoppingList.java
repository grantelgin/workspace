/*
 * July 14, 2013
 * Grant Elgin
 * CS 232 HW3
 * 
 * ShoppingList gets a list of available items, has user input items they want to purchase, verifies the items are available,
 * verifies funds are available and has user set a priority for each item if the total exceeds available funds. 
 * The goShopping method then loops through items in order of priority 1st, then by cost to show remaining balance after each item.  
 */

import java.util.Scanner;

public class ShoppingList {

	private double totalCost;
	private String name;
	
	private static Scanner keyboard = new Scanner( System.in );
	
	public static void main(String[] args) {
		System.out.println("Construction project shopping list\nv 1.1\n");
		System.out.println("To start building your shopping list, select an item from the list below.\nTyping the name of the item will add it to your list and display the current price.");
		// show user the items that are available
		showAvailableItems();
		// then get input from user to build shopping list
		buildItemList();
		
	}
	
	public ShoppingList() {
		totalCost = -1;
		name = "no name";
	}
	
	public static void showAvailableItems() {
		// get available items from the Item class
		String[] availableItems = Item.listAvailableItems();
		// display each of the available items on a line
		for (int x = 0; x < 10; x++) {
			System.out.println(availableItems[x]);
		}
		
		System.out.println();	
	}
	
	public static void buildItemList() {
		Item[] listItem = new Item[7];
		String inputName;
		// prompt user to input the 7 items they want to add to list
		for (int x = 0; x < 7; x++) {
			System.out.print("Enter an item name: ");
			inputName = keyboard.nextLine();
			boolean validItem = true;
			//
			// validate item name
			listItem[x] = Item.validateItem(inputName);
			if (listItem[x].getName().equalsIgnoreCase("no item")) {
				System.out.println("Sorry, that item is not available. Please re-enter an item from the list.");
				x--;
			}
			//
			// check to see if item has already been added to list
			for (int y = 0; y < x; y++){
				if (listItem[y].equals(listItem[x])) {
					System.out.println("You have already added " + listItem[y].getName() + ". To your list. Please select a different item.");
					validItem = false;
					showAvailableItems();
				}
				if (listItem[y].getName().equalsIgnoreCase("no item"))
					System.out.println("Sorry, that item is not available. Please re-enter an item from the list.");
				
			}
			// if item is valid, add to list and continue for loop
			if (validItem)
				System.out.printf("item %1s: %20s price: $%6.2f\n", x + 1, listItem[x].getName(), listItem[x].getPrice());
			// if item is not valid, repeat for current item
			else
				x--;
		}
		// calculate the total cost and display to user
		double listCost = calcTotalCost(listItem);
		System.out.printf("\nTOTAL: $ %6.2f\n", listCost);
		System.out.println();
		
		
		// verify funds are available
		checkFunds(listCost, listItem);
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
			setItemPriority(listItem, funds);
		}
		else
			goShopping(listItem, funds);
	}
		else {
			System.out.println("Woops! Please enter 'default' or 'new'.\n");
			checkFunds(listCost, listItem);
		}
	}
	
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
	
	public boolean equals(ShoppingList otherList) {
		return (this.name.equalsIgnoreCase(otherList.name));
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}
	
}
