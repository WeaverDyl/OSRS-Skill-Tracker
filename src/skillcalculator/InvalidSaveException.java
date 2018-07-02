package skillcalculator;

/**
 * Throws an exception if it's not safe to save. It's not safe to save if:
 * 1. A skill hasn't been selected in the GUI
 * 2. Any textbox is empty
 * @author Dylan Weaver
 *
 */
public class InvalidSaveException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidSaveException(String str) {
		super(str);
	}
}
