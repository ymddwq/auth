package base.exception;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 5007261859115744676L;
	
	private String code;
	private String msg;
	
	public BaseException(String code, String msg) {
		super(msg);
		this.code = code;
		this.msg = msg;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
