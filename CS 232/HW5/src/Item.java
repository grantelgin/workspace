/**
 * Aug 3, 2013
 * Grant Elgin
 * CS 232 HW5
 * 
 * The Item class includes the validateItem method where all available items are defined with a name and a price.
 * The listAvailableItems method returns a list of item names for the user to view.
 */

public class Item {

	private String name;
	private double price;
	private int priority;
	private int qty;
	
	public Item() {
		name ="no item";
		price = 0;
		priority = 99;
		qty = 1;
	}
	
	public static Item validateItem(String name) {
		
		Item thisItem = new Item();

		if (name.equals("lumber")) {	
			thisItem.setName("lumber");
			thisItem.setPrice(34.32);
		}
		
		if (name.equals("screws")) {	
			thisItem.setName("screws");
			thisItem.setPrice(6.47);
		}
		
		if (name.equals("electrical boxes")) {	
			thisItem.setName("electrical boxes");
			thisItem.setPrice(13.80);
		}
		
		if (name.equals("electrical conduit")) {	
			thisItem.setName("electrical conduit");
			thisItem.setPrice(10.58);
		}
		
		if (name.equals("electrical wiring")) {	
			thisItem.setName("electrical wiring");
			thisItem.setPrice(34.97);
		}
		
		if (name.equals("electrical recepticles")) {	
			thisItem.setName("electrical recepticles");
			thisItem.setPrice(1.36);
		}
		
		if (name.equals("plumbing supply pipes")) {	
			thisItem.setName("plumbing supply pipes");
			thisItem.setPrice(9.79);
		}
		
		if (name.equals("plumbing waste pipes")) {	
			thisItem.setName("plumbing waste pipes");
			thisItem.setPrice(8.24);
		}
		
		if (name.equals("sheet rock")) {	
			thisItem.setName("sheet rock");
			thisItem.setPrice(9.98);
		}
		
		if (name.equals("wall finishes")) {	
			thisItem.setName("wall finishes");
			thisItem.setPrice(24.98);
		}
		
		if (name.equals("exit")) {
			System.exit(0);
		}
			
		
		return thisItem;
		
	}
	
	public static String[] listAvailableItems() {
		String[] items = new String[10];
		items[0] = "lumber";
		items[1] = "screws";
		items[2] = "electrical boxes";
		items[3] = "electrical conduit";
		items[4] = "electrical wiring";
		items[5] = "electrical recepticles";
		items[6] = "plumbing supply pipes";
		items[7] = "plumbing waste pipes";
		items[8] = "sheet rock";
		items[9] = "wall finishes";
		
		return items;
		
	}
	
	public boolean equals(Item otherItem) {
			return (this.name.equalsIgnoreCase(otherItem.name));
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}


	public int getQty() {
		return qty;
	}


	public void setQty(int qty) {
		this.qty = qty;
	}
	

}
