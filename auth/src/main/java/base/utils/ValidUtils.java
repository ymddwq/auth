package base.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import base.exception.ExceptionInfo;
import base.result.DataResult;

public class ValidUtils {
	
	/**
	 * 如果检验失败将全部结果封装到DataResult中
	 * @param bindingResult
	 * @param dataResult
	 */
	public static boolean validResultBulid(BindingResult bindingResult, DataResult dataResult) {
		List<FieldError> allErrors = bindingResult.getFieldErrors();
		Map<String, String> map = new HashMap<String, String>();
		if(allErrors != null && allErrors.size()>0) {
			for(FieldError fe : allErrors) {
				map.put(fe.getField(), fe.getDefaultMessage());
			}
			dataResult.setCodeMsg(ExceptionInfo.VALID_FAIL);
			dataResult.setData(map);
			return false;
		}
		return true;
	}
	
}
