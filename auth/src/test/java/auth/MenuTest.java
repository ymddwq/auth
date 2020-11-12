package auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import auth.model.Menu;
import auth.service.MenuService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})
public class MenuTest {
	
	@Autowired
	MenuService menuService;
	
	@Test
	public void insert() {
		Menu obj = new Menu();
		
//		obj.setName(new Date().toString());
//		menuService.insert(obj);
		
		obj.setName("test1");
		menuService.insert(obj);
		Menu obj1 = new Menu();
		obj1.setName("test1-1");
		obj1.setPid(obj.getId());
		menuService.insert(obj1);
		Menu obj2 = new Menu();
		obj2.setName("test1-2");
		obj2.setPid(obj.getId());
		menuService.insert(obj2);
		Menu obj3 = new Menu();
		obj3.setName("test1-1-1");
		obj3.setPid(obj1.getId());
		menuService.insert(obj3);
		Menu obj4 = new Menu();
		obj4.setName("test1-1-2");
		obj4.setPid(obj1.getId());
		menuService.insert(obj4);
	}
	
	@Test
	public void updateByPrimaryKey() {
//		Menu obj = new Menu();
//		obj.setId(2);
//		obj.setName("test2_u");
//		try {
//			menuService.updateByPrimaryKey(obj);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	@Test
	public void deleteByPrimaryKey() {
		menuService.deleteByPrimaryKey(1);
	}
	
	@Test
	public void selectAll() {
		menuService.selectAll();
	}
	
	@Test
	public void selectAllMenuPermissions() {
		menuService.selectAllMenuPermissions(null);
	}
	
}
