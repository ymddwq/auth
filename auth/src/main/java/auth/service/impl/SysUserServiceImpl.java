package auth.service.impl;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import auth.form.SysUserForm;
import auth.mapper.SysUserMapper;
import auth.model.Role;
import auth.model.SysUser;
import auth.model.SysUserPermission;
import auth.model.SysUserRole;
import auth.service.SysUserService;
import base.exception.ExceptionInfo;
import base.service.impl.BaseServiceImpl;
import base.utils.RSAUtil;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, SysUserForm, SysUserMapper> implements SysUserService {
	
	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	//TODO 待补充，将session改为spring-session，修改或删除用户时要更新或者删除该用户session
	//待补充，在不同浏览器登录同一帐号时，需要挤出
	@Override
	public SysUser sysUserLogin(HttpServletRequest request, SysUser record) throws Exception {
		//decryptByPrivateKey(record);
		SysUser user = mapper.selectSysUserByNameAndPassword(record);
		if(user != null) {
			//存入redis 规则为 SYSUSER_ 加上 用户id
			redisTemplate.opsForValue().set("SYSUSER_" + request.getSession().getId(), user, 30, TimeUnit.MINUTES);
			//TODO 将该用户的权限放入redis
			List<SysUserPermission> list = mapper.selectPermissionsBySysUserId(user.getId());
			redisTemplate.opsForValue().set("SYSUSER_PERMISSION_" + request.getSession().getId(), list, 30, TimeUnit.MINUTES);
			return user;
		}
		throw ExceptionInfo.USER_PASSWORD_ERROR;
	}
	
	@Override
	public boolean sysUserLogout(HttpServletRequest request) {
		redisTemplate.delete("SYSUSER_" + request.getSession().getId());
		redisTemplate.delete("SYSUSER_PERMISSION_" + request.getSession().getId());
		request.getSession().invalidate();
		return true;
	}
	
	@Override
	public boolean insert(SysUser record) throws Exception {
		logger.info(this.getClass().getName() + " insert record: " + record);
		//decryptByPrivateKey(record);
		//新增user
		int result = mapper.insert(record);
		if(result == 1) {
			//新增roles
			if(!CollectionUtils.isEmpty(record.getRoles())) {
				List<SysUserRole> surs = new ArrayList<SysUserRole>();
				for(Role role : record.getRoles()) {
					SysUserRole sur = new SysUserRole();
					sur.setUserId(record.getId());
					sur.setRoleId(role.getId());
					surs.add(sur);
				}
				if(surs.size() > 0) {
					mapper.insertSysUserRoles(surs);
				}
			}
			return true;
		}
		return false;
	}
	
	public void decryptByPrivateKey(SysUser record) throws Exception {
		//私钥解密
		String privateKey = (String) redisTemplate.opsForValue().get(RSAUtil.KEY + record.getPublicKey());
		//校验key是否超时
		if(privateKey == null) {
			throw ExceptionInfo.REDIS_KEY_TIMEOUT;
		}
		String password = RSAUtil.decryptByPrivateKey(privateKey, record.getPassword());
		//md5加密
	    MessageDigest md5=MessageDigest.getInstance("MD5");
	    password = Base64.encodeBase64String(md5.digest(password.getBytes("utf-8")));
	    record.setPassword(password);
	}

	@Override
	public boolean deleteByPrimaryKey(Integer id) {
		logger.info(this.getClass().getName() + " deleteByPrimaryKey id: " + id);
		if(1 == mapper.deleteByPrimaryKey(id)) {
			//删除用户角色表中此userId对应的数据
			mapper.deleteSysUserRoleBySysUserId(id);
			return true;
		}
		return false;
	}
	
	//TODO 修改密码功能待补充   校验功能待补充
	@Override
	public boolean updateByPrimaryKey(SysUser record) {
		logger.info(this.getClass().getName() + " updateByPrimaryKey record: " + record);
		int result = mapper.updateByPrimaryKey(record);
		if(1 == result) {
			//删除原用户对应的角色
			mapper.deleteSysUserRoleBySysUserId(record.getId());
			//新增角色所对应的权限
			List<Role> roles = record.getRoles();
			if(!CollectionUtils.isEmpty(roles)) {
				List<SysUserRole> surs = new ArrayList<SysUserRole>();
				for(Role role : roles) {
					SysUserRole sur = new SysUserRole();
					sur.setUserId(record.getId());
					sur.setRoleId(role.getId());
					surs.add(sur);
				}
				mapper.insertSysUserRoles(surs);
			}
			return true;
		} 
		return false;
	}
	
}
