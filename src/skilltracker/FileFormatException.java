package skilltracker;

/**
 * Thrown if a file doesn't have a .txt extension, which is the only
 * supported file extension
 * @author Dylan Weaver
 *
 */
public class FileFormatException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public FileFormatException(String str) {
		super(str);
	}
}
