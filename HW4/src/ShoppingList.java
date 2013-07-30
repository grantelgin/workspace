/**
 * July 25, 2013
 * Grant Elgin
 * CS 232 HW4
 * 
 * ShoppingList gets a list of available items, has user input items they want to purchase, verifies the items are available,
 * verifies funds are available and has user set a priority for each item if the total exceeds available funds.  
 */



public class ShoppingList extends Lister {

	public static void main(String[] args) {
		System.out.println("Construction project shopping list\nv 1.1\n");
		System.out.println("To start building your shopping list, select an item from the list below.\nTyping the name of the item will add it to your list and display the current price.");
		ShoppingList sp = new ShoppingList();
		
		// show user the items that are available
		sp.showAvailableItems();
		// then get input from user to build shopping list
		sp.buildItemList();
		System.out.println("Would you like to build a 2nd list? Enter 'yes' or 'no'");
		String nextList = keyboard.nextLine();
		if (nextList.equalsIgnoreCase("yes")) {	
		ShoppingList sp2 = new ShoppingList();
		
		// show user the items that are available
		sp2.showAvailableItems();
		// then get input from user to build shopping list
		sp2.buildItemList();
		}
		else
			System.exit(0);
	}
	
	public void buildItemList() {
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
	
	
	
}
