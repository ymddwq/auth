package base.result;

import base.exception.BaseException;

public class DataResult {
	
	private String code;
	private Object msg;
	private Object data;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getMsg() {
		return msg;
	}
	public void setMsg(Object msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
	public void setCodeMsg(BaseException be) {
		this.code = be.getCode();
		this.msg = be.getMsg();
	}
	
}
