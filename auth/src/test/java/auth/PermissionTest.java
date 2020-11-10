package auth;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import auth.form.PermissionForm;
import auth.model.Permission;
import auth.service.PermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})

public class PermissionTest {
	
	@Autowired
	PermissionService permissionService;
	
	@Test
	public void insert() {
		for(int i=0; i<=10; i++) {
			try {
				Permission obj = new Permission();
				obj.setName("t" + i + new Date().toString());
				obj.setUrl("permission" + i);
				permissionService.insert(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void updateByPrimaryKey() {
//		Permission obj = new Permission();
//		obj.setId(5);
//		obj.setName("test" + new Date().toString());
//		try {
//			permissionService.updateByPrimaryKey(obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void deleteByPrimaryKey() {
		try {
			permissionService.deleteByPrimaryKey(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectAll() {
		try {
			PermissionForm form = new PermissionForm();
			permissionService.selectAll(form);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
