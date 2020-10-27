package auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import auth.form.RoleForm;
import auth.model.Role;
import auth.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})

public class RoleTest {
	
	@Autowired
	RoleService roleService;
	
	@Test
	public void insert() {
		for(int i=0; i<=10; i++) {
			try {
				Role obj = new Role();
				obj.setName("test" + i);
				roleService.insert(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void updateByPrimaryKey() {
//		Role obj = new Role();
//		obj.setId(24);
//		obj.setName("test" + new Date().toString());
//		try {
//			roleService.updateByPrimaryKey(obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void deleteByPrimaryKey() {
		try {
			roleService.deleteByPrimaryKey(24);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void selectAll() {
		try {
			RoleForm form = new RoleForm();
			roleService.selectAll(form);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
