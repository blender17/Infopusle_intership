package parser;

public class ParseException extends Exception {
	private final String excOccured;

	ParseException(String message, String excOccured) {
		super(message);
		this.excOccured = excOccured;
	}

	public String getTextToParse () {
		return excOccured;
	}
}