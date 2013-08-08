
public class IntegerException extends Exception {

	/**
	 * Grant Elgin
	 * HW6
	 */
	private static final long serialVersionUID = 1L;

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
