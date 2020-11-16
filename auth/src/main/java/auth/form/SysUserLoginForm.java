package auth.form;

import javax.validation.constraints.NotNull;

public class SysUserLoginForm {
	
	@NotNull(message="名称不能为空")
	private String name;

	@NotNull(message="密钥不能为空")
	private String publicKey;
	
	@NotNull(message="密码不能为空")
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}