package mod.xtronius.ttm.util;

public class StringHelper {
	
	public static String splitCamelCase(String s) {
		   return s.replaceAll(
		      String.format("%s|%s|%s",
		         "(?<=[A-Z])(?=[A-Z][a-z])",
		         "(?<=[^A-Z])(?=[A-Z])",
		         "(?<=[A-Za-z])(?=[^A-Za-z])"
		      ),
		      " "
		   );
	}
	
	public static String removeSpaces(String s) { return s.replaceAll("\\s+", ""); }
}
