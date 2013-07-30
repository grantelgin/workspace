
public class Item {
	private String name = "no item";
	private double price = 0;
	
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
}