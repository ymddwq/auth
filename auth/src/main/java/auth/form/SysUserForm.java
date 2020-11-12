package auth.form;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import auth.model.Permission;
import base.form.PageForm;

public class SysUserForm extends PageForm {
	
	@NotNull(message="名称不能为空")
	@Length(message="名称最少为2个字符，最长为32个字符", min=2, max=32)
	private String name;

	@NotNull(message="密码不能为空")
	@Length(message="密码最少为6个字符，最长为32个字符", min=6, max=32)
	private String password;
	
	@NotNull(message="重复密码不能为空")
	@Length(message="重复密码最少为6个字符，最长为32个字符", min=6, max=32)
	private String password1;
	
	@Pattern(regexp = "^[0-1]", message="请填写正确的值")
	private String enabled;
	
	private Date createdStartTime;
	
	private Date createdEndTime;
	
	private List<Permission> permissions;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public Date getCreatedStartTime() {
		return createdStartTime;
	}

	public void setCreatedStartTime(Date createdStartTime) {
		this.createdStartTime = createdStartTime;
	}

	public Date getCreatedEndTime() {
		return createdEndTime;
	}

	public void setCreatedEndTime(Date createdEndTime) {
		this.createdEndTime = createdEndTime;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<Permission> permissions) {
		this.permissions = permissions;
	}
	
}
