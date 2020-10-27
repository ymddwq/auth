package auth;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import auth.form.ModuleForm;
import auth.model.Module;
import auth.service.ModuleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})

public class ModuleTest {
	
	@Autowired
	ModuleService moduleService;
	
	@Test
	public void insert() {
		try {
			Module module = new Module();
			module.setName("test" + new Date().toString());
			moduleService.insert(module);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void updateByPrimaryKey() {
//		Module module = new Module();
//		module.setId(2);
//		module.setName("test" + new Date().toString());
//		try {
//			moduleService.updateByPrimaryKey(module);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void deleteByPrimaryKey() {
		try {
			moduleService.deleteByPrimaryKey(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectAll() {
		try {
			ModuleForm module = new ModuleForm();
			moduleService.selectAll(module);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
