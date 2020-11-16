package base.utils;

import org.apache.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;

import base.exception.ExceptionInfo;

/**
 * redis锁工具类，需要将value设置为对象类型（Lock），存放更多内容以便扩充，后续补充。
 * 后续更改使用Redisson实现（Redisson可使用Redlock实现分布式锁（无法保证百分百成功））
 * @author lz
 *
 */
public class AuthRedisLockUtils {
	
	public static final String REDIS_LOCK_PREFIX = "RedisLock_";
	public static final String REDIS_LOCK_VERSION_PREFIX = "RedisLock_Version";
	public static final Integer REDIS_LOCK_RETRY_TIME_INTERVAL = 500;//重试时间间隔，单位毫秒
	
	private static Logger logger = Logger.getLogger(AuthRedisLockUtils.class);
	
	/**
	 * 版本二：存在问题，此版本不为key设置失效时间，如果程序出现问题（比如释放锁失败），释放锁操作未执行，那么redis中会存在不过期的key，导致后续线程获取不到锁。
	 * 暂时使用此版本，如出现问题，人工介入。自旋间隔实现方式可以优化，后期补充（比如时间间隔递增，或者呈指数倍递增等等）。
	 * @param redisTemplate
	 * @param key
	 * @param value
	 * @param timeout
	 * @return
	 * @throws Exception
	 */
	public static Boolean redisLock(RedisTemplate<String, Object> redisTemplate, String key, String value, Integer timeout) throws Exception {
		String redisKey = "RedisLock_" + key;
		//设置自旋次数
		Integer count = timeout*1000/REDIS_LOCK_RETRY_TIME_INTERVAL;
		while(true) {
			count--;
			//1.将key value存入redis，并返回是否成功
			Boolean b = redisTemplate.opsForValue().setIfAbsent(redisKey, value);
			if(b!=null && b) {
				logger.info("AuthRedisUtils redisLock lockName:" + redisKey + "  ThreadId:" + Thread.currentThread().getId() + "加redis锁成功!");
				System.out.println("AuthRedisUtils redisLock lockName:" + redisKey + "  ThreadId:" + Thread.currentThread().getId() + "加redis锁成功!");
				return true;
			} else {
				if(count==0) {
					logger.error("AuthRedisUtils redisLock lockName:" + redisKey + "  ThreadId:" + Thread.currentThread().getId() + "获取redis锁失败!");
					throw ExceptionInfo.GET_REDIS_LOCK_TIMEOUT;
				}
				logger.info("AuthRedisUtils redisLock lockName:" + redisKey + "  ThreadId:" + Thread.currentThread().getId() + "尝试重新获取redis锁!");
				Thread.sleep(REDIS_LOCK_RETRY_TIME_INTERVAL);
			}
		}
	}
	
	//释放锁需优化，如果出现网络波动造成的释放锁失败，应尝试重试，后期补充。
	public static void redisUnlock(RedisTemplate<String, Object> redisTemplate, String key, String value) {
		String redisKey = "RedisLock_" + key;
		redisTemplate.delete(redisKey);
	}
		
	
//	/**
//	 * 版本一：存在问题，假设线程a执行时间超过设置的过期时间，那么后面的线程可以获取锁，造成锁失效。
//	 * @param key
//	 * @param value
//	 * @param timeout 超时时间（秒）
//	 * @return
//	 * @throws Exception
//	 */
//	public static Boolean redisLock(RedisTemplate<String, Object> redisTemplate, String key, String value, Long timeout) throws Exception {
//		String redisKey = "RedisLock_" + key;
//		//将超时时间设置为5倍
//		Long redisTimeOut = timeout * 5;
//		//自旋
//		while(true) {
//			//1.将key value存入redis，并设置过期时间
//			String luaString = "return redis.call('SET' , KEYS[1] , ARGV[1] , 'NX', 'EX' , ARGV[2])";
//			DefaultRedisScript<String> script = new DefaultRedisScript<String>();
//			script.setResultType(String.class);
//			script.setScriptSource(new StaticScriptSource(luaString));
//			List<String> list = new ArrayList<>();
//			list.add(redisKey);
//			String result = redisTemplate.execute(script, list, value, redisTimeOut.toString());
//			
//			if(result != null) {
//				logger.info("AuthRedisUtils redisLock lockName:" + redisKey + "  ThreadId:" + Thread.currentThread().getId() + "加redis锁成功!");
//				System.out.println("AuthRedisUtils redisLock lockName:" + redisKey + "  ThreadId:" + Thread.currentThread().getId() + "加redis锁成功!");
//				return true;
//			}
//			
//			//如果在5倍timeout时间还没有执行完，就抛异常，不再重试
//			Long nowValue = redisTemplate.getExpire(redisKey, TimeUnit.SECONDS);
//			if(nowValue < timeout) {
//				logger.info("AuthRedisUtils redisLock lockName:" + redisKey + "  ThreadId:" + Thread.currentThread().getId() + "获取redis锁失败!");
//				throw ExceptionInfo.GET_REDIS_LOCK_TIMEOUT;
//			}
//			
//			logger.info("AuthRedisUtils redisLock lockName:" + redisKey + "  ThreadId:" + Thread.currentThread().getId() + "尝试重新获取redis锁!");
//			
//			Thread.sleep(REDIS_LOCK_RETRY_TIME_INTERVAL);
//		}
//	}
//	
//	public static void redisUnlock(RedisTemplate<String, Object> redisTemplate, String key, String value) {
//		String redisKey = "RedisLock_" + key;
//	    // 使用lua脚本进行原子删除操作
//	    String luaString = "if redis.call('get', KEYS[1]) == ARGV[1] then " +
//	                                "return redis.call('del', KEYS[1]) " +
//	                                "else " +
//	                                "return 0 " +
//	                                "end";
//	    DefaultRedisScript<Long> script = new DefaultRedisScript<Long>();
//		script.setResultType(Long.class);
//		script.setScriptSource(new StaticScriptSource(luaString));
//		List<String> list = new ArrayList<>();
//		list.add(redisKey);
//	    redisTemplate.execute(script, list, value);
//	}
	
}


















