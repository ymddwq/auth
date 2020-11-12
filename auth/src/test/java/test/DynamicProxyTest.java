package test;

import test.base.Handler;
import test.base.Proxy;

public class DynamicProxyTest {
	
	public static void main(String[] args) throws Exception {
		Handler handler = new HandlerImpl(new PorxyTestServiceImpl());
		PorxyTestService obj = (PorxyTestService) Proxy.newProxyInstance(PorxyTestService.class, handler);
		obj.addUser();
	}
	
}
