package skillcalculator;

public class InvalidSaveException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidSaveException(String str) {
		super(str);
	}
}
