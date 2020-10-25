package HelloWorld;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import HelloWorld.model.RoleModel;
import HelloWorld.service.HelloWorldService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})
public class HelloWorldTest {

	private static Logger logger = Logger.getLogger(HelloWorldTest.class);
	
	@Autowired
	HelloWorldService helloWorldService;
	
	@Test
	public void selectById() {
		RoleModel roleModel = helloWorldService.selectById(1L);
		System.out.println(roleModel.getName());
	}
	
	@Test
	public void selectAll() {
		helloWorldService.selectAll();
	}
	
	@Test
	public void insert() {
		RoleModel roleModel = new RoleModel();
		roleModel.setName("test" + new Date().toString());
		helloWorldService.insert(roleModel);
	}
	
}
