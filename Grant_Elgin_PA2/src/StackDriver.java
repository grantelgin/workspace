import java.util.Scanner;

/**
 * 
 * @author grantelgin StackDriver methods draw the board, listen for input,
 *         validate input, and save the validated move to a StackManager object
 * 
 */

public class StackDriver {
	private StackManager currentStack = new StackManager();
	public Scanner keyboard = new Scanner(System.in);

	public static void main(String[] args) {
		StackDriver me = new StackDriver();
		me.doIt();
	}

	public void doIt() {
		showInstructions();
		showBoard();
		listenForInput();
	}
	
	public void showInstructions() {
		System.out.println("8 queens v 1.0\n");
		System.out.println("Select a column and a row for each position.\nThe goal is locate 8 queens on the board where they can not kill each other.");
		System.out.println("Enter 999 to exit. ");
	}

	public void listenForInput() {
		// user inputs an int for column, then an int for row. calls to
		// checkLocation validate the input.
		System.out.println("Choose a column: ");
		try {
			int col = keyboard.nextInt();
			if (col == 999){
				System.out.println("Exiting game...");
				System.exit(0);
			}
			if (col > 0 && col < 9) {
				System.out.println("Choose a row: ");
				int row = keyboard.nextInt();
				if (row == 999) {
					System.out.println("Exiting game...");
					System.exit(0);
				}
				if (row > 0 && row < 9) {
					System.out.println("column: " + col + "\nrow: " + row);
					checkLocation(col, row);
				} else {
					System.out
							.println("Woops! Please enter a number between 1 and 8");
					listenForInput();
				}
			} else {
				System.out
						.println("Woops! Please enter a number between 1 and 8");
				listenForInput();
			}
		} catch (Exception e) {
			System.out
					.println("Woops! Something went wrong. Please enter a number between 1 and 8");
			keyboard.nextLine();
			listenForInput();
		}
	}

	public void checkLocation(int col, int row) {
		// check the input against current entries in currentStack. Any
		// stackNode that matches col or row returns false.
		// if valid, check the diagonals and set boolean success. If success,
		// add the node to the stack.
		StackNode node = currentStack.getTop();
		boolean valid = true;
		for (int x = 0; x < currentStack.getCount(); x++) {
			if (col == node.getColumn() || row == node.getRow()) {
				System.out.println("Nope. Already a queen there");
				valid = false;
				listenForInput();
			} else {
				node = node.getNext();
				valid = true;
			}
		}
		if (valid) {
			// check diagonal
			boolean success = (topLeft(col, row) && topRight(col, row)
					&& bottomLeft(col, row) && bottomRight(col, row));

			if (success) {
				// Add to currentStack
				System.out.println("success ");
				StackNode move = new StackNode();
				move.setLocation(col, row);
				currentStack.push(move);
				
				if (currentStack.getCount() == 8) {
					System.out.println("You win!\n");
					showBoard();
					System.exit(0);
				} else
					showBoard();
					listenForInput();
			} else {
				listenForInput();
			}
		}

	}

	public boolean topLeft(int col, int row) {
		// check for queens on the top left diagonal
		boolean success = true;
		StackNode currentNode = currentStack.getTop();
		while (col > 0 && row > 0) {
			for (int x = 0; x < currentStack.getCount(); x++) {
				if (col == currentNode.getColumn()
						&& row == currentNode.getRow()) {
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
		// check for queens on the top right diagonal
		boolean success = true;
		StackNode currentNode = currentStack.getTop();
		while (col > 0 && col < 9 && row > 0 && row < 9) {
			for (int x = 0; x < currentStack.getCount(); x++) {
				if (col == currentNode.getColumn()
						&& row == currentNode.getRow()) {
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
		// check for queens on the bottom left diagonal
		boolean success = true;
		StackNode currentNode = currentStack.getTop();
		while (col < 9 && col > 0 && row > 0 && row < 9) {
			for (int x = 0; x < currentStack.getCount(); x++) {
				if (col == currentNode.getColumn()
						&& row == currentNode.getRow()) {
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
		// check for queens on the bottom right diagonal
		boolean success = true;
		StackNode currentNode = currentStack.getTop();
		while (col < 9 && col > 0 && row < 9 && row > 0) {
			for (int x = 0; x < currentStack.getCount(); x++) {
				if (col == currentNode.getColumn()
						&& row == currentNode.getRow()) {
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
		System.out.println("Drawing board...");
		String row = "+---+---+---+---+---+---+---+---+";
		String queen = " Q |";
		String emptySquare = "   |";
		String board = row;
		String newLine = "|";
		StackNode currentNode = currentStack.getTop();
		boolean[][] bo = new boolean[8][8];
		// build an array of true false values based on currentStack. 
		// true draws a queen, false draws an emptySquare
		for (int x = 0; x < currentStack.getCount(); x++) {
			bo[currentNode.getRow() - 1][currentNode.getColumn() - 1] = true;
			currentNode = currentNode.getNext();
		}

		for (int r = 0; r < 8; r++) {
			for (int c = 0; c < 8; c++) {
				if (bo[r][c] == true)
					newLine += queen;
				else
					newLine += emptySquare;
			}

			board = board + "\n" + newLine + "\n" + row;
			newLine = "|";
		}

		System.out.println(board);
	}

}
