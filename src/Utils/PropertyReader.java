package Utils;

import java.util.ResourceBundle;

public class PropertyReader {

	public static String getPropertyValue(String key) {

		String value = "";
		try {
			ResourceBundle rBundle = ResourceBundle.getBundle("app");
			value = rBundle.getString(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}

}
