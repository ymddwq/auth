package auth;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import auth.service.impl.RedisUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})

public class RedisTest {
	
	@Autowired
	RedisUserService redisUserService;
	
	@Test
	public void insert() {
		redisUserService.set("auth01", "1");
		String object = redisUserService.get("auth01").toString();
		System.out.println("<<<<<<<<<<<<<<<<<<<" + object);
	}
	
}
