package skillcalculator;

/**
 * Throws an exception if there was an issue loading. This occurs if there aren't
 * equal numbers of players versus experience vs level data in the saved files.
 * 
 * Generally, this is caused by there being an unresolved player validity problem,
 * meaning that one or more players in the file aren't appearing in the hiscores.
 * @author Dylan Weaver
 *
 */
public class LoadException extends Exception {
	private static final long serialVersionUID = 1L;

	public LoadException(String str) {
		super(str);
	}
}
