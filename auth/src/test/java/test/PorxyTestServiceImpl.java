package test;

public class PorxyTestServiceImpl implements PorxyTestService {

	@Override
	public void addUser() {
		System.out.println("执行 UserService addUser()");
	}

}
