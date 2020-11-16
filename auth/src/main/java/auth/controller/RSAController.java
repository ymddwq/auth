package auth.controller;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import base.exception.ExceptionInfo;
import base.result.DataResult;
import base.utils.AuthStringUtils;
import base.utils.RSAUtil;
import base.utils.RSAUtil.RSAKeyPair;

@Controller
@RequestMapping("/rsa")
public class RSAController {

	private static Logger logger = Logger.getLogger(RSAController.class);
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	//TODO 待优化，需要加入ip mac等，防止同一客户多次调用
	@RequestMapping("/getPublicKey")
	@ResponseBody
	public DataResult getPublicKey() {
		DataResult dataResult = new DataResult();
		try {
			RSAKeyPair generateKeyPair = RSAUtil.generateKeyPair();
			//将密钥存入redis
			redisTemplate.opsForValue().set(RSAUtil.KEY + generateKeyPair.getPublicKey(), generateKeyPair.getPrivateKey(), 10, TimeUnit.MINUTES);
			//将公钥返回
			dataResult.setData(generateKeyPair.getPublicKey());
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("RSAController getPublicKey Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
}
