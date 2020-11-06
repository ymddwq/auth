package test;

import java.lang.reflect.Method;

import test.base.Handler;

public class HandlerImpl implements Handler {
	private Object target;
	
	public HandlerImpl(Object target) {
		super();
		this.target = target;
	}
	
	@Override
	public void invoke(Object o, Method method) throws Exception {
		System.out.println("执行前");
		method.invoke(target);
		System.out.println("执行后");
	}

}
