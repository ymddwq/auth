package auth.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import auth.form.MenuForm;
import auth.model.Menu;
import auth.service.MenuService;
import base.exception.BaseException;
import base.exception.ExceptionInfo;
import base.result.DataResult;
import base.utils.AuthStringUtils;
import base.utils.ValidUtils;

@Controller
@RequestMapping("/menu")
public class MenuController {

	private static Logger logger = Logger.getLogger(MenuController.class);
	
	@Autowired
	MenuService menuService;
	
	@RequestMapping("/select")
	@ResponseBody
	public DataResult selectAll() {
		DataResult dataResult = new DataResult();
		List<Menu> lists = null;
		try {
			lists = menuService.selectAll();
			dataResult.setData(lists);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("MenuController selectAll " + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/insert")
	@ResponseBody
	public Object insert(@Valid MenuForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		Menu obj = new Menu();
		try {
			BeanUtils.copyProperties(form, obj);
			menuService.insert(obj);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (BaseException e) {
			logger.error("MenuController insert BaseException" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.THE_SAME_NAME);
		} catch (Exception e) {
			logger.error("MenuController insert Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
	@RequestMapping("/update")
	@ResponseBody
	public Object update(@Valid MenuForm form, BindingResult bindingResult) {
		DataResult dataResult = new DataResult();
		//如果参数校验失败直接返回
		if(!ValidUtils.validResultBulid(bindingResult, dataResult)) {
			return dataResult;
		}
		if(StringUtils.isEmpty(form.getId())) {
			dataResult.setCodeMsg(ExceptionInfo.ID_IS_NULL);
			return dataResult;
		}
		Menu obj = new Menu();
		try {
			BeanUtils.copyProperties(form, obj);
			menuService.updateByPrimaryKey(obj);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (BaseException e) {
			logger.error("MenuController update BaseException" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.THE_SAME_NAME);
		} catch (Exception e) {
			logger.error("MenuController update Exception" + AuthStringUtils.printStackTraceToString(e));
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
			menuService.deleteByPrimaryKey(id);
			dataResult.setCodeMsg(ExceptionInfo.SUCCESS);
		} catch (Exception e) {
			logger.error("MenuController delete Exception" + AuthStringUtils.printStackTraceToString(e));
			dataResult.setCodeMsg(ExceptionInfo.FAIL);
		}
		return dataResult;
	}
	
}
