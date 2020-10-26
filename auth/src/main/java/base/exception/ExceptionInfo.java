package base.exception;

public class ExceptionInfo {
	
	public static final BaseException SUCCESS = new BaseException("000000", "成功");
	public static final BaseException FAIL = new BaseException("000001", "失败");
	public static final BaseException VALID_FAIL = new BaseException("000002", "校验失败");
	
	public static final BaseException THE_SAME_NAME = new BaseException("100000", "已存在名称相同的记录");
	public static final BaseException ID_IS_NULL = new BaseException("100001", "id不能为空");
	
}
