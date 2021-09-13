package json;

public class JSONParser {

	public static JSONObject parseToJSONObject(String jsonString) throws ParseException {
		String json = getValue(jsonString);
		String objStr = innerText(json);
		return createObject (objStr);
	}

	private static JSONObject createObject (String JsonString) throws ParseException {
		String[] strPairs = splitByComma(JsonString);
		JSONObject obj = new JSONObject();
		for (String pair : strPairs) {
			JSONElement element = createJSONElem(pair);
			obj.addElement(element);
		}
		return obj;
	}

	/*returns array with 2 values:
	1 value - arrays quantity
	2 value - objects quantity
	(negative value means incorrect json)*/
	public static int[] countArraysAndObjects(String json) {
		int[] count = new int[2];
		int objectOpen = 0;
		int objectClose = 0;
		int arrayOpen = 0;
		int arrayClose = 0;
		char[] jsonArray = innerText(json).replaceAll("\\s+", "").toCharArray();
		for (int i = 0; i < jsonArray.length; i++) {
			if (jsonArray[i] == ':') {
				i++;
				if (jsonArray[i] == '{') {
					objectOpen++;
				}
			}
			switch (jsonArray[i]) {
				case '}' -> objectClose++;
				case '[' -> arrayOpen++;
				case ']' -> arrayClose++;
			}
		}
		if ((arrayOpen - arrayClose) != 0) {
			count[0] = -1;
		} else {
			count[0] = arrayOpen;
		}
		if ((objectOpen - objectClose) != 0) {
			count[1] = -1;
		} else {
			count[1] = objectOpen;
		}
		return count;
	}

	private static JSONElement createJSONElem(String jsonElem) throws ParseException {
		int index = getColonIndex(jsonElem);
		Token token;
		String value;
		String key = null;
		if (index != -1) {
			 key = innerText(getValue(jsonElem.substring(0, index)));
			index++;
			value = getValue(jsonElem.substring(index));
			token = createToken(value);
		} else {
			 token = createToken(jsonElem);
			 value = jsonElem;
		}
		switch (token.type) {
			case INTEGER -> {
				return new JSONElement(key, Long.parseLong(token.value));
			}
			case DOUBLE -> {
				return new JSONElement(key, Double.parseDouble(token.value));
			}
			case STRING -> {
				return new JSONElement(key, innerText(value));
			}
			case BOOLEAN -> {
				return new JSONElement(key, Boolean.parseBoolean(token.value));
			}
			case OBJECT -> {
				String text = innerText(token.value);
				JSONObject object = createObject(text);
				return new JSONElement(key, object);
			}
			case ARRAY -> {
				String text = innerText(token.value);
				String[] textobjs = splitByComma(text);
				String netobj;
				int count = textobjs.length;
				JSONObject[] objects = new JSONObject[count];
				for (int n = 0; n < count; n++) {
					netobj = getValue(textobjs[n]);
//					objects[n] = createObject(innerText(netobj));
					objects[n] = createObject(netobj);
				}
				return new JSONElement(key, objects);
			}
			case NULL -> {
				return new JSONElement(key);
			}
		}
		throw new ParseException("JSON parse exception", jsonElem);
	}

	private static int getColonIndex(String jsonString) {
		char chr;
		int index = 0;
		while (index < jsonString.length()) {
			chr = jsonString.charAt(index);
			if (chr == ':') {
				return index;
			}
			index++;
		}
		return -1;
	}

	private static Token createToken(String jsonString) throws ParseException {
		Token token = new Token();
		String text = getValue(jsonString);
		token.value = text;
		if (isInteger(text)) {
			token.type = JSONType.INTEGER;
			return token;
		}
		if (isDouble(text)) {
			token.type = JSONType.DOUBLE;
			return token;
		}
		if (isString(text)) {
			token.type = JSONType.STRING;
			return token;
		}
		if (isNull(text)) {
			token.type = JSONType.NULL;
			return token;
		}
		if (isBoolean(text)) {
			token.type = JSONType.BOOLEAN;
			return token;
		}
		if (isJSONObj(text)) {
			token.type = JSONType.OBJECT;
			return token;
		}
		if (isJSONArr(text)) {
			token.type = JSONType.ARRAY;
			return token;
		}
		throw new ParseException("JSON parse exception", jsonString);
	}

	private static String getValue(String jsonString) {
		int length = jsonString.length();
		int beginIndex = 0;
		int endIndex = 0;
		char chr;
		for (int i = 0; i < length; i++) {
			beginIndex = i;
			chr = jsonString.charAt(i);
			if ((chr == '\t') || (chr == '\n') || (chr == '\r') || (chr == ' ')) {
				continue;
			}
			break;
		}
		for (int i = length - 1; i >= 0; i--) {
			chr = jsonString.charAt(i);
			if ((chr == '\t') || (chr == '\n') || (chr == '\r') || (chr == ' ')) {
				continue;
			}
			endIndex = i;
			break;
		}
		return jsonString.substring(beginIndex, endIndex + 1);
	}

	private static boolean isNull(String jsonString) {
		return (jsonString.compareToIgnoreCase("null") == 0);
	}

	private static boolean isBoolean(String jsonString) {
		if (jsonString.compareToIgnoreCase("true") == 0) {
			return true;
		}
		return (jsonString.compareToIgnoreCase("false") == 0);
	}

	private static boolean isInteger(String text) {
		char chr;
		for (int n = 0; n < text.length(); n++) {
			chr = text.charAt(n);
			if ((chr >= '0' && chr <= '9') || (chr == '-')) {
				continue;
			}
			return false;
		}
		for (int n = 1; n < text.length(); n++) {
			chr = text.charAt(n);
			if (chr == '-') {
				return false;
			}
		}
		if (text.length() == 1)
			return text.charAt(0) != '-';
		return true;
	}

	private static boolean isDouble (String text) {
		char chr;
		boolean isNegative = false;
		boolean numFound = false;
		int dotCount = 0;
		int dotIndex = 1;

		for (int i = 0; i < text.length(); i++) {
			chr = text.charAt(i);
			if (chr >= '0' && chr <= '9') {
				numFound = true;
				continue;
			}
			if (chr == '-') {
				isNegative = true;
				continue;
			}
			if (chr == '.') {
				dotCount++;
				dotIndex = i;
				continue; }
			return false;
		}
		if (!numFound) {
			return false;
		}
		if (dotCount > 1) {
			return false;
		}

		for (int i = 1; i < text.length(); i++) {
			chr = text.charAt(i);
			if (chr == '-') {
				return false;
			}
		}

		if (!isNegative && dotIndex == 0) {
			return false;
		}
		if (isNegative && dotIndex == 1) {
			return false;
		}
		return dotIndex != text.length() - 1;
	}

	private static boolean isString(String text) {
		int length = text.length();
		if (length < 2) {
			return false;
		}
		char chr;
		int qcp = 0;
		chr = text.charAt(0);
		if ((chr == '"') || (chr == '\'')) {
			qcp = chr;
		}
		if (qcp == 0){
			return false;
		}
		chr = text.charAt(length -1);
		if (chr != qcp){
			return false;
		}
		for (int i = 1; i < length -1; i++) {
			chr = text.charAt(i);
			if (chr == qcp)
				if (text.charAt(i - 1) != '\\'){
					return false;
				}
		}
		return true;
	}

	private static boolean isJSONObj(String text) {
		int length = text.length();
		if (length < 2) return false;
		char chr;
		chr = text.charAt(0);
		if (chr != '{') {
			return false;
		}
		chr = text.charAt(length -1);
		return chr == '}';
	}

	private static boolean isJSONArr(String text) {
		int length = text.length();
		if (length < 2) {
			return false;
		}
		char chr;
		chr = text.charAt(0);
		if (chr != '[') {
			return false;
		}
		chr = text.charAt(length -1);
		return chr == ']';
	}

	private static String innerText (String text) {
		int length = text.length();
		if (length < 3) return "";
		return text.substring(1, length - 1);
	}

	private static String[] splitByComma(String jsonString) {
		int length = jsonString.length();
		char chr;
		int index = 0;
		int childrenOpen;
		int textOpen;
		int singleOpen = 0;
		int doubleOpen = 0;
		int bracesOpen = 0;
		int bracketOpen = 0;
		int beginIndex = 0;
		String[] splittedJson = new String[0];
		while (index < length) {
			childrenOpen = bracesOpen + bracketOpen;
			textOpen = singleOpen + doubleOpen;
			chr = jsonString.charAt(index);
			switch (chr) {
				case '"' -> {
					if (childrenOpen > 0) {
						break;
					}
					if (doubleOpen == 0) {
						doubleOpen++;
						break;
					}
					if (jsonString.codePointBefore(index) == '\\') {
						break;
					}
					doubleOpen--;
				}
				case '\'' -> {
					if (childrenOpen > 0) {
						break;
					}
					if (singleOpen == 0) {
						singleOpen++;
						break;
					}
					if (jsonString.codePointBefore(index) == '\\') {
						break;
					}
					singleOpen--;
				}
				case '{' -> {
					if (textOpen > 0) {
						break;
					}
					bracesOpen++;
				}
				case '}' -> {
					if (textOpen > 0) {
						break;
					}
					bracesOpen--;
				}
				case '[' -> {
					if (textOpen > 0) {
						break;
					}
					bracketOpen++;
				}
				case ']' -> {
					if (textOpen > 0) {
						break;
					}
					bracketOpen--;
				}
				case ',' -> {
					if (textOpen > 0) {
						break;
					}
					if (childrenOpen > 0) {
						break;
					}
					String text = jsonString.substring(beginIndex, index);
					splittedJson = appendArray(splittedJson, text);
					beginIndex = index + 1;
				}
			}
			index++;
		}
		String text = jsonString.substring(beginIndex, index);
		splittedJson = appendArray(splittedJson, text);
		return splittedJson;
	}

	private static String[] appendArray(String[] parts, String text) {
		int length = text.length();
		char chr;
		boolean notEmpty = false;
		for (int i = 0; i < length; i++) {
			chr = text.charAt(i);
			if ((chr == '\t') || (chr == '\n') || (chr == '\r') || (chr == ' ')) {
				continue;
			}
			notEmpty = true;
		}
		if (!notEmpty) {
			return parts;
		}
		int count = parts.length;
		String[] array = new String[count + 1];
		System.arraycopy(parts, 0, array, 0, count);
		array[count] = text;
		return array;
	}

	private static class Token {
		JSONType type = JSONType.NONE;
		String value = null;
	}
}

