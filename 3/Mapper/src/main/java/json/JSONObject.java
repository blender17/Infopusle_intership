package json;

import java.util.ArrayList;
import java.util.List;

public class JSONObject {

	List<JSONElement> elementsList;

	public JSONObject() {
		elementsList = new ArrayList<>();
	}

	public void addElement(JSONElement element) {
		elementsList.add(element);
	}

	public JSONElement pop() {
		if (!elementsList.isEmpty()) {
			return elementsList.remove(0);
		}
		else return null;
	}

	public JSONElement getElement(String name) {
		for (JSONElement element : elementsList) {
			if (element.getName().equals(name)) {
				return element;
			}
		}
		return null;
	}


}
