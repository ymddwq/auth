package HelloWorld;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import HelloWorld.form.RoleFrom;
import HelloWorld.model.RoleModel;
import HelloWorld.service.HelloWorldService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml", "classpath:spring/dispatcher-servlet.xml"})
public class HelloWorldTest {

	@Autowired
	HelloWorldService helloWorldService;
	
	@Test
	public void selectById() {
		try {
			helloWorldService.selectById(1L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectAll() {
		RoleFrom roleFrom = new RoleFrom();
		try {
			helloWorldService.selectAll(roleFrom);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void insert() {
		RoleModel roleModel = new RoleModel();
		roleModel.setName("test" + new Date().toString());
		try {
			helloWorldService.insert(roleModel);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
