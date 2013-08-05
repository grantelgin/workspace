public class aintnoShoppingList
{
	private ListNode head;
	
	public aintnoShoppingList()
	{
		head = null;
	}
	
	/**
	Displays the data on the list
	*/
	public void showList()
	 {
		ListNode position = head;
		while (position != null)
		{
			System.out.println(position.getData());
			position = position.getLink();
		}
	 }
	 
	 public int length()
	 { 
		int count  = 0;
		ListNode position = head;
		while (position != null)
		{
			count++;
			position = position.getLink();
		}
		return count;
	 }
	 
	 public void addANodeToStart(Item addData)
	 {
		head = new ListNode(addData, head);
	 }
	 
	 public void deleteHeadNode()
	 {
		if (head != null)
			head = head.getLink();
		else
		{
			System.out.println("Deleting from an empty list.");
			System.exit(0);
		}
	 }
	 
	 public boolean onList(Item target)
	 {  
		return find(target) != null; 
	 }
	 
	 private ListNode find(Item target)
	 {
		boolean found = false; 
		ListNode position = head; 
		while ((position != null) && !found)
		{
			Item dataAtPosition = position.getData();
			if (dataAtPosition.equals(target))
				found = true;
			else 
				position = position.getLink();
		}
		return position;
	 }
	 

}