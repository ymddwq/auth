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
import base.utils.StringUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})
public class HelloWorldTest {

	private static Logger logger = Logger.getLogger(HelloWorldTest.class);
	
	@Autowired
	HelloWorldService helloWorldService;
	
	@Test
	public void selectById() {
		logger.info("HelloWorldTest selectById test");
		try {
			helloWorldService.selectById(1L);
		} catch (Exception e) {
			logger.error(StringUtils.printStackTraceToString(e));
		}
	}
	
	@Test
	public void selectAll() {
		logger.info("HelloWorldTest selectAll test");
		RoleFrom roleFrom = new RoleFrom();
		try {
			helloWorldService.selectAll(roleFrom);
		} catch (Exception e) {
			logger.error(StringUtils.printStackTraceToString(e));
		}
	}
	
	@Test
	public void insert() {
		logger.info("HelloWorldTest insert test");
		RoleModel roleModel = new RoleModel();
		roleModel.setName("test" + new Date().toString());
		try {
			helloWorldService.insert(roleModel);
		} catch (Exception e) {
			logger.error(StringUtils.printStackTraceToString(e));
		}
	}
	
}
