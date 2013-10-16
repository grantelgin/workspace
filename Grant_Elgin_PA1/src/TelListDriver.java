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
		System.out.println("Enter a command");
		keyboard.reset();
		String cmd = keyboard.nextLine();
		if (!cmd.isEmpty()) {
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
			TelListItem found = currentList.searchByName(name);
			System.out.println("Found: " + found.getName() + "\nEmail: " + found.getEmail() +  "\nPhone number: " + found.getPhoneNumber());
			
		}
		else if (cmd.toLowerCase().startsWith("e")) {
			// add listen for arguments
			System.out.println("Enter an email address: ");
			String email = keyboard.nextLine();
			TelListItem found = currentList.searchByEmail(email);
			System.out.println("Found: " + found.getName() + "\nEmail: " + found.getEmail() +  "\nPhone number: " + found.getPhoneNumber());
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
			System.out.println("Uh oh - wrong comand! Please enter a command from the menu.");
		}
		
		listenForCommand();
		
	}
	
	public void  collectAddInfo() {
		TelListItem data = new TelListItem();
		System.out.println("Enter name: ");
		data.setName(keyboard.nextLine());
		System.out.println("Enter email for " + data.getName() + ":");
		data.setEmail(keyboard.nextLine()); 
		System.out.println("Enter phone number for " + data.getName() + ":");
		data.setPhoneNumber(keyboard.nextLine());
		boolean result = confirmAdd(data);
		
		if (result) {
			TelListItem added = currentList.searchByName(data.getName());
			System.out.println("Added to list\nName: " + added.getName() + "\nEmail: " + added.getEmail() + "\nNumber: " + added.getPhoneNumber());
			listenForCommand();
		}
		else
			listenForCommand();
			
		
	}
	
	private boolean confirmAdd(TelListItem data) {
		System.out.println("Is this correct?");
		System.out.println("Name: " + data.getName() + "\nEmail: " + data.getEmail() + "\nNumber: " + data.getPhoneNumber() + "\nEnter Y/N");
		boolean result = true;
		String confirm = keyboard.next();
		if (confirm.toLowerCase().startsWith("y")) {
			//TelListItem data = new TelListItem()
			System.out.println("Adding...\n");
			currentList.headAdd(data);
			
		}
		else if (confirm.toLowerCase().startsWith("n")) {
			System.out.println("cancelled...\n");
			result = false;
		}
		else {
			System.out.println("Woops! Please enter Y or N\n");
			confirmAdd(data);
		}
		
		return result;
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
