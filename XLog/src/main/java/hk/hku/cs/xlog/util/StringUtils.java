package hk.hku.cs.xlog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringUtils {

	private final static Pattern p = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#�?…�?&*（）—�?\\-+|{}【�?‘；：�?“�?。，、？]");

	/**
	 * 
	 * @param str
	 * @return String
	 */
	public static String filterString(String str) {
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

}
