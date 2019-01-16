package br.com.a5.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionUtil {

	public static String stackTraceToString(Throwable t) {
		if (t != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			return sw.toString();
		} else {
			return null;
		}
	}

}
