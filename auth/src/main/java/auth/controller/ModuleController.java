package auth.controller;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import auth.form.ModuleForm;
import auth.model.Module;
import auth.service.ModuleService;
import base.exception.ExceptionInfo;
import base.result.DataResult;
import base.utils.AuthStringUtils;
import base.utils.ValidUtils;

@Controller
@RequestMapping("/module")
public class ModuleController {

	private static Logger logger = Logger.getLogger(ModuleController.class);
	
	@Autowired
	ModuleService moduleService;
	
	@RequestMapping("/select")
	@ResponseBody
	public DataResult selectAll(ModuleForm form) {
		DataResult dataResult = new DataResult();
		PageInfo<Module> list = null;
		try {
			list = moduleService.selectAll(form);
			dataResult.setData(list);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("ModuleController selectAll Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(@Valid ModuleForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		Module module = new Module();
		try {
			BeanUtils.copyProperties(form, module);
			moduleService.insert(module);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("ModuleController insert Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@Valid ModuleForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		if(form != null && StringUtils.isEmpty(form.getId())) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		if(StringUtils.isEmpty(form.getId())) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		Module module = new Module();
		try {
			BeanUtils.copyProperties(form, module);
			moduleService.updateByPrimaryKey(module);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("ModuleController update Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Object delete(Integer id) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(StringUtils.isEmpty(id)) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		try {
			moduleService.deleteByPrimaryKey(id);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("ModuleController delete Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
}
