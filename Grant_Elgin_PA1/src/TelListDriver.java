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
			// add listen for arguments
			collectAddInfo();
		}
		else if (cmd.toLowerCase().startsWith("p")) {
			// add listen for arguments
			System.out.println("gonna print list");
			currentList.printList();
		}
		else if (cmd.toLowerCase().startsWith("s")) {
			// add listen for arguments
			System.out.println("Enter a name: ");
			String name = keyboard.nextLine();
			currentList.searchByName(name);
			System.out.println("Searchin for name");
		}
		else if (cmd.toLowerCase().startsWith("e")) {
			// add listen for arguments
			System.out.println("searchin email");
		}
		else if (cmd.toLowerCase().startsWith("d")) {
			// add listen for arguments
			System.out.println("deletin");
		}
		else if (cmd.toLowerCase().startsWith("w")) {
			// add listen for arguments
			System.out.println("writin");
		}
		else if (cmd.toLowerCase().startsWith("r")) {
			// add listen for arguments
			System.out.println("restorin");
		}
		else
			System.out.println("Uh oh - wrong comand! Please enter a commend from the menu.");
		
		cmd = null;
		doIt();
	}
	
	public void  collectAddInfo() {
		System.out.println("Enter name: ");
		String name = keyboard.nextLine();
		System.out.println("Enter email for " + name + ":");
		String email = keyboard.nextLine();
		System.out.println("Enter phone number for " + name + ":");
		String number = keyboard.nextLine();
		System.out.println("Is this correct?");
		System.out.println("Name: " + name + "\nEmail: " + email + "\nNumber: " + number + "\nEnter Y/N");
		String confirm = keyboard.next();
		if (confirm.toLowerCase().startsWith("y")) {
			TelListItem data = new TelListItem();
			data.setName(name);
			data.setEmail(email);
			data.setPhoneNumber(number);
			System.out.println("gonna add to list");
			currentList.headAdd(data);
			TelListItem added = currentList.searchByName(name);
			System.out.println("Added to list\nName: " + added.getName() + "\nEmail: " + added.getEmail() + "\nNumber: " + added.getPhoneNumber());
			
		}
		else if (confirm.toLowerCase().startsWith("n")) {
			System.out.println("not gonna");
		}
		else {
			System.out.println("back to it");
		}
		confirm = null;
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
