package auth.form;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import base.form.PageForm;

public class ModuleForm extends PageForm {
	
	private Integer id;
	
	@NotNull(message="不能为空")
	@Length(message="名称最少为2个字符，最长为32个字符", min=1, max=32)
	private String name;
	
	@Pattern(regexp = "^[0-1]", message="请填写正确的值")
	private String enabled;
	
	private Date createdStartTime;
	
	private Date createdEndTime;
	
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
	
	@Override
	public String toString() {
		return "ModuleForm [id=" + id + ", name=" + name + ", enabled=" + enabled + ", createdStartTime="
				+ createdStartTime + ", createdEndTime=" + createdEndTime + "]";
	}
	
}
