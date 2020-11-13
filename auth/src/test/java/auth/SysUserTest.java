package auth;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import auth.form.SysUserForm;
import auth.model.SysUser;
import auth.service.SysUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})
public class SysUserTest {
	
	@Autowired
	SysUserService sysUserService;
	
	@Test
	public void insert() throws Exception {
		for(int i=0; i<=10; i++) {
			SysUser obj = new SysUser();
			obj.setName("t" + i + new Date().toString());
			sysUserService.insert(obj);
		}
	}
	
	@Test
	public void updateByPrimaryKey() {
//		SysUser obj = new SysUser();
//		obj.setId(1);
//		obj.setName("test" + new Date().toString());
//		try {
//			sysUserService.updateByPrimaryKey(obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void deleteByPrimaryKey() {
		sysUserService.deleteByPrimaryKey(1);
	}
	
	@Test
	public void selectAll() {
		SysUserForm form = new SysUserForm();
		sysUserService.selectAll(form);
	}
	
	
}
