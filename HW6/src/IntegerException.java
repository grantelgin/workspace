
public class IntegerException extends Exception {

	public IntegerException() {
		System.out.println("Uh oh! Something went wrong! Please enter qty or item priority as an integer.");
	}
	
    public IntegerException(String message) {
        super(message);
    }

    public IntegerException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
