package auth.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import base.form.PageForm;

public class MenuForm extends PageForm {
	
	private Integer id;
	
	@NotNull(message="不能为空")
	@Length(message="名称最少为2个字符，最长为32个字符", min=1, max=32)
	private String name;
	
	@Pattern(regexp = "^[0-1]", message="请填写正确的值")
	private String enabled;
	
	private String pid;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEnabled() {
		return enabled;
	}
	
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
	
	public String getPid() {
		return pid;
	}
	
	public void setPid(String pid) {
		this.pid = pid;
	}
	
}