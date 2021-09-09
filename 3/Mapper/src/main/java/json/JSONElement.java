package json;

public class JSONElement {

	private String name;
	private Object value;
	private JSONType type;

	public JSONElement() {}

	public JSONElement(String name, int value) {
		this.name = name;
		this.value = value;
		this.type = JSONType.INTEGER;
	}

	public JSONElement(String name, long value) {
		this.name = name;
		this.value = value;
		this.type = JSONType.INTEGER;
	}

	public JSONElement(String name, double value) {
		this.name = name;
		this.value = value;
		this.type = JSONType.DOUBLE;
	}

	public JSONElement(String name, String value) {
		this.name = name;
		this.value = value;
		this.type = JSONType.STRING;
	}

	public JSONElement(String name, boolean value) {
		this.name = name;
		this.value = value;
		this.type = JSONType.BOOLEAN;
	}

	public JSONElement(String name, JSONObject value) {
		this.name = name;
		this.value = value;
		this.type = JSONType.OBJECT;
	}

	public JSONElement(String name, JSONObject[] value) {
		this.name = name;
		this.value = value;
		this.type = JSONType.ARRAY;
	}

	public JSONElement(String name) {
		this.name = name;
		this.value = null;
		this.type = JSONType.NULL;
	}

	public String getName() {
		return name;
	}

	public JSONType getType() {
		return type;
	}

	public int getIntValue() {
		if (type == JSONType.INTEGER) {
			return Integer.parseInt(String.valueOf(value));
		}
		return 0;
	}

	public long getLongValue() {
		if (type == JSONType.INTEGER) {
			return Long.parseLong(String.valueOf(value));
		}
		return 0;
	}

	public boolean getBoolValue() {
		if (type == JSONType.BOOLEAN) {
			return (boolean) value;
		}
		return false;
	}

	public double getDoubleValue() {
		if (type == JSONType.DOUBLE) {
			return Double.parseDouble(String.valueOf(value));
		}
		return 0;
	}

	public String getStringValue() {
		return String.valueOf(value);
	}

	public JSONObject getJSONObject() {
		if (type == JSONType.OBJECT) {
			return (JSONObject) value;
		}
		return new JSONObject();
	}

	public JSONObject[] getJSONArray() {
		if (type == JSONType.ARRAY) {
			return (JSONObject[]) value;
		}
		return new JSONObject[0];
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(JSONType type) {
		this.type = type;
	}

	public void setValue(int value) {
		this.value = value;
		type = JSONType.INTEGER;
	}

	public void setValue(long value) {
		this.value = value;
		type = JSONType.INTEGER;
	}

	public void setValue(double value) {
		this.value = value;
		type = JSONType.DOUBLE;
	}

	public void setValue(String value) {
		this.value = value;
		type = JSONType.STRING;
	}

	public void setValue(boolean value) {
		this.value = value;
		type = JSONType.BOOLEAN;
	}

	public void setValue(JSONObject value) {
		this.value = value;
		type = JSONType.OBJECT;
	}

	public void setValue(JSONObject[] value) {
		this.value = value;
		type = JSONType.ARRAY;
	}


}
