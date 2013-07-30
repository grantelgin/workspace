import java.util.Scanner;

public class NumberEntryReport {
	
	private static Scanner keyboard = new Scanner( System.in );
	
	public static void main(String[] args) {
		System.out.println("Hello new user");
		System.out.println("What is your first name?");
		
		String firstName, lastName = "";
		firstName = keyboard.next();
		System.out.println("Hi " + firstName + ". What is your last name?");
		String lastNameInput = keyboard.next();
	
		for (int x = 0; x < lastNameInput.length(); x++) {
				     char aChar = lastNameInput.charAt(x);
				     lastName = lastName + aChar;
				}
		//if (UserInputValidation.onlyLetters(lastName))
			System.out.println("Nice to meet you, " + firstName + " " + lastName + ".");
		//else {
		//	System.out.println("The report encountered an error validating your input. Please restart to try again.");
		//	System.exit(0);
		//}
		numberEntry(firstName, lastName);
	}
	
	public static void numberEntry(String firstName, String lastName) {
		System.out.println();
		System.out.println("===========================================");
		System.out.println("Number Entry Report for " + firstName + " " + lastName);
		System.out.println("===========================================");
		System.out.println();
		System.out.println(firstName + ", Please enter a total of 7 numbers to generate the report. Enter only one number per line and press return to submit each number.");
		System.out.println("Enter an Integer and press return to start:");
		int val1 = keyboard.nextInt();
		LowestVal = val1;
		System.out.println("Input     Highest     Lowest     Line Total     Average");
		System.out.println("-------------------------------------------------------");
		String line1 = calculate(val1, 0);
		System.out.println(line1);
		System.out.println("Enter a Double and press return.");
		double val2 = keyboard.nextDouble();
		System.out.println("Input     Highest     Lowest     Line Total     Average");
		System.out.println("-------------------------------------------------------");
		String line2 = calculate(0, val2);
		System.out.println(line2);
		System.out.println("Enter an Integer and press return.");
		int val3 = keyboard.nextInt();
		System.out.println("Input     Highest     Lowest     Line Total     Average");
		System.out.println("-------------------------------------------------------");
		String line3 = calculate(val3, 0);
		System.out.println(line3);
		System.out.println("Enter an Integer and press return.");
		int val4 = keyboard.nextInt();
		System.out.println("Input     Highest     Lowest     Line Total     Average");
		System.out.println("-------------------------------------------------------");
		String line4 = calculate(val4, 0);
		System.out.println(line4);
		System.out.println("Enter an Integer and press return.");
		int val5 = keyboard.nextInt();
		System.out.println("Input     Highest     Lowest     Line Total     Average");
		System.out.println("-------------------------------------------------------");
		String line5 = calculate(val5, 0);
		System.out.println(line5);
		System.out.println("Enter an Integer and press return.");
		int val6 = keyboard.nextInt();
		System.out.println("Input     Highest     Lowest     Line Total     Average");
		System.out.println("-------------------------------------------------------");
		String line6 = calculate(val6, 0);
		System.out.println(line6);
		System.out.println("Enter an Integer and press return.");
		int val7 = keyboard.nextInt();
		System.out.println("Input     Highest     Lowest     Line Total     Average");
		System.out.println("-------------------------------------------------------");
		String line7 = calculate(val7, 0);
		System.out.println();
		System.out.println("=======================================================");
		System.out.println("Input     Highest     Lowest     Line Total     Average");
		System.out.println("-------------------------------------------------------");
		System.out.println(line1);
		System.out.println(line2);
		System.out.println(line3);
		System.out.println(line4);
		System.out.println(line5);
		System.out.println(line6);
		System.out.println(line7);
		System.out.println();
		System.out.println("Grand total of all input numbers:  " + val1 + val2 + val3 + val4 + val5 + val6 + val7);
		
	}
	public static double totalVal = 0;
	public static double PrevVal = 0;
	public static double HighestVal = 0;
	public static double LowestVal;
	public static double AvVal = 0;
	public static String calculate(int valInt, double valDbl) {
		String lineResult = "";
		if (valInt != 0) {
			totalVal = totalVal + valInt;
			if (valInt > HighestVal)
				HighestVal = valInt;
			if (valInt < LowestVal)
				LowestVal = valInt;
			AvVal = totalVal / 7;
			
			double lineTotal = valInt + HighestVal + LowestVal;
			lineResult = valInt + "        " + HighestVal + "        " + LowestVal + "        " + lineTotal + "        " + AvVal;
			
		}
		if (valDbl != 0) {
			totalVal = totalVal + valDbl;
			if (valDbl > HighestVal)
				HighestVal = valDbl;
			if (valDbl < LowestVal)
				LowestVal = valDbl;
			AvVal = totalVal / 7;
			
			double lineTotal = valDbl + HighestVal + LowestVal;
			lineResult = valDbl + "     " + HighestVal + "     " + LowestVal + "     " + lineTotal + "     " + AvVal;
		}
		
		return lineResult;
	}

	

}
