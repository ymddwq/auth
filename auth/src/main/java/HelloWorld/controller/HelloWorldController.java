package HelloWorld.controller;

import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import HelloWorld.form.RoleFrom;
import HelloWorld.model.RoleModel;
import HelloWorld.service.HelloWorldService;
import base.exception.ExceptionInfo;
import base.result.DataResult;
import base.utils.AuthStringUtils;

@Controller
public class HelloWorldController {

	private static Logger logger = Logger.getLogger(HelloWorldController.class);
	
	@Autowired
	HelloWorldService helloWorldService;
	
	@RequestMapping("helloWorld_select")
	@ResponseBody
	public DataResult selectById(Integer id) {
		DataResult dataResult = new DataResult();
		if(StringUtils.isEmpty(id)) {
			id = 1;
		}
		RoleModel role = null;
		try {
			role = helloWorldService.selectById(id);
			dataResult.setData(role);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("HelloWorldController selectById " + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("helloWorld_selectAll")
	@ResponseBody
	public DataResult selectAll(RoleFrom roleFrom) {
		DataResult dataResult = new DataResult();
		PageInfo<RoleModel> roles = null;
		try {
			roles = helloWorldService.selectAll(roleFrom);
			dataResult.setData(roles);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("HelloWorldController selectAll " + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("helloWorld_save")
	@ResponseBody
	public Object insertRole(RoleModel role) {
		DataResult dataResult = new DataResult();
		RoleModel roleModel = new RoleModel();
		roleModel.setName("test" + new Date().toString());
		try {
			helloWorldService.insert(roleModel);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("HelloWorldController insertRole " + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
}
