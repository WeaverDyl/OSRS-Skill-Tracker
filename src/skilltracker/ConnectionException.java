package skilltracker;

/**
 * Thrown when a connection can't be made. This is due to a player not
 * existing in the hiscores.
 * @author Dylan
 *
 */
public class ConnectionException extends Exception {
	private static final long serialVersionUID = 1L;

	public ConnectionException(String str) {
		super(str);
	}
}
