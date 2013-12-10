
public class HashDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashDriver me = new HashDriver();
		me.doIt();
	}

	public void doIt() {
		OpenHashTable table = new OpenHashTable();
		
		// Table size is 31
		table.add("23");
		table.add("233");
		table.add("2333");
		System.out.println(table);
		
		System.out.println(table.search("233"));
		System.out.println(table.search("234"));
		table.delete("233");
		System.out.println(table.delete("2"));
		System.out.println(table);
	}
}
