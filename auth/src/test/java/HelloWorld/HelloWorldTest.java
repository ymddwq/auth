package HelloWorld;

import java.util.Date;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import HelloWorld.form.RoleFrom;
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
		logger.info("HelloWorldTest selectById test");
		RoleModel roleModel = helloWorldService.selectById(1L);
		System.out.println(roleModel.getName());
	}
	
	@Test
	public void selectAll() {
		logger.info("HelloWorldTest selectAll test");
		RoleFrom roleFrom = new RoleFrom();
		logger.info("HelloWorldTest selectAll roleFrom:" + roleFrom);
		helloWorldService.selectAll(roleFrom);
	}
	
	@Test
	public void insert() {
		logger.info("HelloWorldTest insert test");
		RoleModel roleModel = new RoleModel();
		roleModel.setName("test" + new Date().toString());
		logger.info("HelloWorldTest insert roleModel:" + roleModel);
		helloWorldService.insert(roleModel);
	}
	
}