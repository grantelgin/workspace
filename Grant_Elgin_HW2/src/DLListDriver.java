

public class DLListDriver {

	public static void main(String[] args) {
		DLListDriver me = new DLListDriver();
		me.doIt();
	}
	
	public void doIt() {
		// use the linked list to establish locations on a delivery route.
		DLList list = new DLList();
		
		// start with 3 addresses
		list.headAdd("30 Beacon St");
		list.headAdd("20 Main St");
		list.headAdd("1 Main St");
		System.out.println(list);
		
		// route must end at loading dock
		list.tailAdd("end at loading dock");
		// route must begin at office
		list.headAdd("office");
		System.out.println(list);
		
		// insert new locations into most efficient point in delivery queue
		// set the data of a new DLNode object to match the data of the address we want to stop before the new address.
		DLNode after = new DLNode();
		after.setData("20 Main St");
		list.afterAdd(after, "154 Vassar St");
		System.out.println(list);
		
		after.setData("30 Beacon St");
		list.afterAdd(after, "32 Beacon St");
		System.out.println(list);
		
		after.setData("154 Vassar St");
		list.afterAdd(after, "77 Mass Ave");
		System.out.println(list);
		
		after.setData("office");
		list.afterAdd(after, "27 1st Ave");
		System.out.println(list);
	}

}
