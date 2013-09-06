public class ListNode
{
	private Item data;
	private ListNode link;

	public ListNode() 
	{
		data = null;
		link = null;
	}
	
	public ListNode(Item newData, ListNode linkValue) {
		data = newData;
		link = linkValue;
	}
	
	public void setData(Item newData) {
		data = newData;
	 }
	 
	 public Item getData() {
		return data;
	 }
	 
	 public void setLink(ListNode newLink)
	 { 
		link = newLink;
	 }

	 public ListNode getLink() {
		return link;
	 }
}
