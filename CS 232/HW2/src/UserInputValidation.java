
public class UserInputValidation {
	public static boolean onlyLetters (String userInput) {
		String input = userInput.toLowerCase();
		while (!input.matches("[abcdefghijklmnopqrstuvwxyz]")) {
			System.out.println("Please enter only letters");
			return false;
		}
		if (input != "exit")
			return true;
		else
			System.exit(0);
			return false;
	}
}
