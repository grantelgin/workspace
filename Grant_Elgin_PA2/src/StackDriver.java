import java.util.Scanner;


public class StackDriver {
	private StackManager currentStack = new StackManager();  
	public Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args) {
		StackDriver me = new StackDriver();
		me.doIt();
	}
	
	public void doIt() {
		showBoard();
		listenForInput();
		
	}
	
	public void listenForInput() {
		System.out.println("Choose a column: ");
		try {
			int col = keyboard.nextInt();
			if (col > 0 && col < 9) {
				System.out.println("Choose a row: ");
				int row = keyboard.nextInt();
				if (row > 0 && row < 9) {
					System.out.println("column: " + col + "\nrow: " + row);
					checkLocation(col, row);
				}
				else {
					System.out.println("Woops! Please enter a number between 1 and 8");
					listenForInput();
				}
			}
			else {
				System.out.println("Woops! Please enter a number between 1 and 8");
				listenForInput();
			}
		}
		catch (Exception e) {
			System.out.println("Woops! Please enter a number between 1 and 8");
			keyboard.nextLine();
			listenForInput();
		}
	}
	
	public void checkLocation(int col, int row) {
		StackNode node = currentStack.getTop();
		boolean valid = true;
		for (int x = 0; x < currentStack.getCount(); x++) {
			if (col == node.getColumn() || row == node.getRow()) {
				System.out.println("Nope. Already a queen there");
				valid = false;
				listenForInput();
			}
			else {
				node = node.getNext();
				valid = true;
			}
		}
		if (valid) {
			//check diagonal
			boolean success = topLeft(col, row);
			success = topRight(col, row);
			success = bottomLeft(col, row);
			success = bottomRight(col, row);
			
			if (success) {
				System.out.println("success: ");
				StackNode move = new StackNode();
				move.setLocation(col, row);
				currentStack.push(move);
				System.out.println("col: " + currentStack.getTop().getColumn() + "\nrow: " + currentStack.getTop().getRow());
				listenForInput();
			}
			else {
				listenForInput();
			}
		}
		
	}
	
	public boolean checkDiagonal(int col, int row) {
		boolean success = true;
		StackNode node = new StackNode();
		for (int x = 0; x < currentStack.getCount(); x++) {
			
			if (col == node.getColumn() || row == node.getRow()) {
				System.out.println("Nope. Already a queen there");
				success = false;
			}
		}
		
		return success;
	}
	
	public boolean topLeft(int col, int row) {
		boolean success = true;
		StackNode currentNode = currentStack.getTop();
		while (col > 0 && row > 0) {
			for (int x = 0; x < currentStack.getCount(); x++) {
				if (col == currentNode.getColumn() && row == currentNode.getRow()) {
					System.out.println("Nope. Queen on top left diagonal");
					return false;
				}
			}
			
			if (success) {
				col = col - 1;
				row = row - 1;
			}
		}
		
		return success;
	}
	
	public boolean topRight(int col, int row) {
		boolean success = true;
		StackNode currentNode = currentStack.getTop();
		while (col > 0 && row < 9) {
			for (int x = 0; x < currentStack.getCount(); x++) {
				if (col == currentNode.getColumn() && row == currentNode.getRow()) {
					System.out.println("Nope. Queen on top right diagonal");
					return false;
				}
			}
			
			if (success) {
				col = col + 1;
				row = row - 1;
			}
		}
		
		return success;
	}
	
	public boolean bottomLeft(int col, int row) {
		boolean success = true;
		StackNode currentNode = currentStack.getTop();
		while (col < 9 && row > 0) {
			for (int x = 0; x < currentStack.getCount(); x++) {
				if (col == currentNode.getColumn() && row == currentNode.getRow()) {
					System.out.println("Nope. Queen on bottom left diagonal");
					return false;
				}
			}
			
			if (success) {
				col = col - 1;
				row = row + 1;
			}
		}
		
		return success;
	}
	
	public boolean bottomRight(int col, int row) {
		boolean success = true;
		StackNode currentNode = currentStack.getTop();
		while (col < 9 && row < 9) {
			for (int x = 0; x < currentStack.getCount(); x++) {
				if (col == currentNode.getColumn() && row == currentNode.getRow()) {
					System.out.println("Nope. Queen on bottom right diagonal");
					return false;
				}
			}
			
			if (success) {
				col = col + 1;
				row = row + 1;
			}
		}
		
		return success;
	}
	
	public void showBoard() {
		String row = "+---+---+---+---+---+---+---+---+";
		String queen = " Q |";
		String emptySquare = "   |";
		
		System.out.println(row);
		System.out.println("|" + queen + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare);
		System.out.println(row);
		System.out.println("|" + queen + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare);
		System.out.println(row);
		System.out.println("|" + queen + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare);
		System.out.println(row);
		System.out.println("|" + queen + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare);
		System.out.println(row);
		System.out.println("|" + queen + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare);
		System.out.println(row);
		System.out.println("|" + queen + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare);
		System.out.println(row);
		System.out.println("|" + queen + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare);
		System.out.println(row);
		System.out.println("|" + queen + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare + emptySquare);
		System.out.println(row);
	}

}
