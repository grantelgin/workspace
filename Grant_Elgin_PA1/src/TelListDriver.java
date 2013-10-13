import java.util.Scanner;


public class TelListDriver {
	private TelListManager currentList = new TelListManager();
	Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {

		TelListDriver tld = new TelListDriver();
		tld.doIt();
	}
	
	public void doIt () {
		printInstructions();
		listenForCommand();
		//currentList.writeOut(currentList, "");
	}
	
	public void listenForCommand() {
		String cmd = keyboard.nextLine();
		if (cmd.toLowerCase().startsWith("a")) {
			System.out.println("gonna add phone");
		}
		else if (cmd.toLowerCase().startsWith("p")) {
			System.out.println("gonna print list");
		}
		else if (cmd.toLowerCase().startsWith("s")) {
			System.out.println("Searchin for name");
		}
		else if (cmd.toLowerCase().startsWith("e")) {
			System.out.println("searchin email");
		}
		else if (cmd.toLowerCase().startsWith("d")) {
			System.out.println("deletin");
		}
		else if (cmd.toLowerCase().startsWith("w")) {
			System.out.println("writin");
		}
		else if (cmd.toLowerCase().startsWith("r")) {
			System.out.println("restorin");
		}
		else
			System.out.println("Uh oh - wrong comand! Please enter a commend from the menu.");
		
		cmd = null;
		doIt();
	}
	
	public void printInstructions() {
		System.out.println("Telephone List Manager v1.0");
		System.out.println("Menu");
		printMenu();
	}
	
	public void printMenu() {
		System.out.printf("%-3sAdd a phone number to the list\n", "a");
		System.out.printf("%-3sPrint the entire list\n", "p");
		System.out.printf("%-3sSearch for a name\n", "s");
		System.out.printf("%-3sSearch for email address\n", "e");
		System.out.printf("%-3sDelete an Entry\n", "d");
		System.out.printf("%-3sWrite the database to a file\n", "w");
		System.out.printf("%-3sRestore a saved database\n", "r");	
	}

}
