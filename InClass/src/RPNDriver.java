
	import java.util.Scanner;


	public class RPNDriver {

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			RPNDriver me = new RPNDriver();
			me.doIt();
		}

		public void doIt() {
			StackManager st = new  StackManager();
			
			@SuppressWarnings("resource")
			Scanner keyboard = new Scanner(System.in);
			

		boolean quit = false;
		
		String input = "";
		
		while (quit == false)
		{
		System.out.println("Please enter next item:");
		
		input = keyboard.nextLine().trim().toLowerCase();
		
		if (input.equals("+")) st.push(st.pop() + st.pop());
		
		else if (input.equals("*")) st.push(st.pop() * st.pop());
		
		else if (input.equals("-")) st.push(st.pop() - st.pop());
		
		else if (input.equals("/")) st.push((1/st.pop()) * st.pop());
		
		else if (input.equals("%")) st.push(st.pop() % st.pop());
		
		else if (input.equals("m")) st.push((-1) * st.pop());
		
		else if (input.equals("s"))
		{
			double double1 = st.pop();
			double double2 = st.pop();
			st.push(double1);
			st.push(double2);
		}
		
		else if (input.equals("d"))
		{
			double double1 = st.pop();
			st.push(double1);
			st.push(double1);
		}
		
		else if (input.equals("p")) System.out.println("Top -> " + st.getTop().getOperand());
		
		else if (input.equals("n"))
		{
			if (st.isEmpty()) System.out.println("Empty!");
			else {
			System.out.println("Top -> " +st.getTop().getOperand());
			st.pop();
			}
		}
		
		else if (input.equals("f")) System.out.println(st);
		
		else if (input.equals("h") || input.equals("?")) printHelp();
		
		else if (input.equals("q")) System.exit(0);
		
		else if (input.equals("c")) while (st.getTop() != null) st.pop();
		
		else
		{
			try
			{
				st.push(Double.parseDouble(input));	
			}
			catch (Exception e)
			{
				System.out.println("Invalid Entry!  Please try again!");
			}
			
		}
		

		}
	}

		public void printHelp() {
			System.out.printf("\n%-6s%s", "cmd", "Description");
			System.out.printf("\n%-6s%s", "XX.XX", "Place a number on the stack");
			System.out.printf("\n%-6s%s", "+", "Add the top two items");
			System.out.printf("\n%-6s%s", "*", "Multiply the top two items");
			System.out.printf("\n%-6s%s", "-", "Subtract the top item from the next item");
			System.out.printf("\n%-6s%s", "/", "Divide the second item by the top item");
			System.out.printf("\n%-6s%s", "%", "Integer remainder when dividing the second item by the top item");
			System.out.printf("\n%-6s%s", "m", "Unary minus -- negate the top item");
			System.out.printf("\n%-6s%s", "s", "Swap the top two items");
			System.out.printf("\n%-6s%s", "d", "Duplicate top item on stack");
			System.out.printf("\n%-6s%s", "p", "Print (to the screen) the top item");
			System.out.printf("\n%-6s%s", "n", "Print and remove the top item");
			System.out.printf("\n%-6s%s", "f", "Print all the contents of the stack");
			System.out.printf("\n%-6s%s", "c", "Clear the Stack");
			System.out.printf("\n%-6s%s", "q", "Quit");
			System.out.printf("\n%-6s%s", "h or ?", "Print a help message.");
			
		}	
		
	}
	
	




