package auth.model;

import base.model.BaseModel;

public class Module extends BaseModel {

	private String name;
	private String enabled;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled == null ? null : enabled.trim();
	}

}