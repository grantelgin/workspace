import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/** 
 * @author grantelgin
 *
 * BubbleSort contains methods for sorting ArrayList<Item> objects and Item[] arrays.
 */
public class BubbleSort {
	
	public static Scanner keyboard = new Scanner( System.in );
	
	// ArrayList method
	public static void sortBubble(ArrayList<Item> al, FundsAccount funds) {
		int x;
		boolean flag = true;
		Item temp;
		
		//printPriority(listItem);
		
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
		double totalCost = Lister.calcTotalCost(al);
		ShoppingList.printItemTable(al, totalCost);
		ShoppingList.goShopping(al, funds);
	}
	
	//Item[] method
	public static void setBubble(Item[] listItem, FundsAccount funds) {
		int[] sortedPriority = new int[7];

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
				System.out.println(e.getMessage());

				inputInt = keyboard.nextInt();
			}
					
		}
		sortBubble(listItem, funds);
		ShoppingList.goShopping(listItem, funds);
	}
	
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

	/* For debugging - print out priority for each item in the list. 
	 * public static void printPriority(Item[] listItem) {
		for (int x = 0; x < listItem.length; x++) {
			System.out.print(listItem[x].getPriority() + " ");
		}
		System.out.println();
	}*/
	
}
