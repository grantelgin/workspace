
public class PrintOut extends Lister {
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
			totalItemCost = Lister.calcTotalItemCost(theLinkedList.find(dataAtPosition).getData().getPrice(), theLinkedList.find(dataAtPosition).getData().getQty());
			printRow(1, theLinkedList.find(dataAtPosition).getData().getPriority(), theLinkedList.find(dataAtPosition).getData().getName(), theLinkedList.find(dataAtPosition).getData().getPrice(), theLinkedList.find(dataAtPosition).getData().getQty(), totalItemCost);
			position = position.getLink();
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

	@Override
	public void buildItemList() throws IntegerException {
		// TODO Auto-generated method stub
		
	}
}
