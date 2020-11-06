package test;

import test.base.Handler;
import test.base.Proxy;

public class DynamicProxyTest {
	
	public static void main(String[] args) throws Exception {
		Handler handler = new HandlerImpl(new UserServiceImpl());
		UserService obj = (UserService) Proxy.newProxyInstance(UserService.class, handler);
		obj.addUser();
	}
	
}
