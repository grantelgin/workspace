import java.util.ArrayList;


public interface PO {
	public void printColumnHeading(String desc1, String desc2, String desc3, String desc4, String desc5, String desc6);
	public void printRow(int desc1, int p, String desc2, double d, int q, double f);
	public void printItemTable(ArrayList<Item> al, double totalCost);
	public void printTableTotal(double total);
	public void printInstructions();
}
