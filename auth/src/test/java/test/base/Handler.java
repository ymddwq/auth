package test.base;

import java.lang.reflect.Method;

public interface Handler {

	public void invoke(Object o, Method method) throws Exception;

}
