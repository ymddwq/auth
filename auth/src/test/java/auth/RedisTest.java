package auth;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import auth.model.Role;
import auth.service.RoleService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})
public class RedisTest {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Autowired
	RoleService roleService;
	
	@Test
	public void testRedisLock() {
		try {
			for(int i=0; i<1000; i++) {
				new Thread(new TestThread()).start();
			}
			Thread.sleep(10000000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	class TestThread implements Runnable {
		@Override
		public void run() {
			try {
				Role obj = new Role();
				obj.setName("test");
				roleService.insert(obj);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void name() {
		System.out.println("name");
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public static void test() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		Constructor<?>[] constructors = RedisTest.class.getConstructors();
		Constructor constructor = constructors[0];
		Object newInstance = constructor.newInstance();
		Method method = newInstance.getClass().getMethod("name");
		method.invoke(newInstance);
	}
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		test();
	}
	
}
