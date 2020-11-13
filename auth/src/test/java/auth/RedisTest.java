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

import base.utils.RSAUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"classpath:spring/applicationContext.xml"})
public class RedisTest {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	public void name() {
		System.out.println("name");
	}
	
	@SuppressWarnings("rawtypes")
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
	
	@Test
	public void test1() {
		String privateKey = (String) redisTemplate.opsForValue().get("test");
		System.out.println(privateKey);
		String privateKey1 = (String) redisTemplate.opsForValue().get("test1");
		System.out.println(privateKey1);
	}
	
	@Test
	public void test2() throws Exception {
		String s = RSAUtil.encryptByPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDZJQU820U4X3jdhY0mMoU57mhd8L0hOOrT2dHfmKB9lHrB3glFxz5bbWzxBBPyaFbPkDlUwx7IHwqTH9b51TIpfqp0v3TDO2j17fKE6XK7UpSHau+j4In6JZ6XAND3td2YLPEonoFeGW8LxUbM2jKYu3xA2yLM4Jcok19jh9ju+QIDAQAB", "abc");
		System.out.println(s);
		String s1 = RSAUtil.decryptByPrivateKey("MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBANklBTzbRThfeN2FjSYyhTnuaF3wvSE46tPZ0d+YoH2UesHeCUXHPlttbPEEE/JoVs+QOVTDHsgfCpMf1vnVMil+qnS/dMM7aPXt8oTpcrtSlIdq76PgifolnpcA0Pe13Zgs8SiegV4ZbwvFRszaMpi7fEDbIszglyiTX2OH2O75AgMBAAECgYARqcxCRgfcbQ+03U89AzEJJFaDBbkswaeAciB7UrwDWxedO4V/kpSE9WK+PDmvJ0OggLfZKEIRv0yp6RYgnnIE/Zhc6TOyEzoKcCI1AmgkEZ8PQCyH+6GFaIwuvclFZjSjDeO17F2VsU71hZptEZ2NjAe+JlpufVwc9sKpeCNksQJBAP+E9Lck2JD7lTeyu0beFj9/DjeTZHgd7nop7BNlKBIwNSemT01pnvllSPJL1l5zLEy5OMtLyy4meyJh79GBvbUCQQDZjZXa2uyXHgPhAUHl8mgyANSfFPobFYKEuPCPNAy/SaJ6qsF+o+rICEfQudBmlrGmpPYxhc+GbIg7LSyBt1a1AkBqclMYMOtfis9hAr5uCXsvPJkJogeK2r1Me0Zz1gJZgWfDes8yqrulngJDnkBR3J0p4Ue9uXeNZE6Lxk0H9Iq5AkA1aVdzRfB0IYBeS4q8Q8llDWMmM2Y8JAnJQAZ/gg3nINLtPuMGjoPj3HSVQCl0JmczPKjc+7c5j4faz3pv1m1JAkEA+sFqVDWYu+Y1W1hHw4IyUpA8eGceO0gkKBa/q4zCNri6Hf6wUNQ4CcFrw5SDs1Nw4Glbfr6LxKDEWTpRxQnJxQ==", s);
		System.out.println(s1);
	}
	
}
