package base.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

public class StringUtils {

	/**
	 * 将异常堆栈信息转换为String类型，以便输出到日志中
	 * @param t
	 * @return
	 */
	public static String printStackTraceToString(Throwable t) {
		StringWriter sw = new StringWriter();
		t.printStackTrace(new PrintWriter(sw, true));
		return sw.getBuffer().toString();
	}
	
}
