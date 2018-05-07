package l18n;

import java.util.Locale;
import java.util.ResourceBundle;

public class L18 {
	public String getLabel(String webname, String weblang) {
		if (weblang.equalsIgnoreCase("ru")) {
			Locale.setDefault(new Locale("ru"));
		} else {
			Locale.setDefault(new Locale("en", "US"));
		}

		ResourceBundle mybundle = ResourceBundle.getBundle("MyLabels");
		mybundle = ResourceBundle.getBundle("MyLabels");

		return mybundle.getString(webname);
	}
}
