package HelloWorld.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import HelloWorld.model.RoleModel;
import HelloWorld.service.HelloWorldService;
import base.result.DataResult;

@Controller
public class HelloWorldController {

	@Autowired
	HelloWorldService helloWorldService;
	
	@RequestMapping("helloWorld_select")
	@ResponseBody
	public DataResult selectById(Model model, Long id) {
		if(StringUtils.isEmpty(id)) {
			id = 1L;
		}
		RoleModel role = helloWorldService.selectById(id);
		DataResult dataResult = new DataResult();
		dataResult.setData(role);
		return dataResult;
	}
	
	@RequestMapping("helloWorld_selectAll")
	@ResponseBody
	public DataResult selectById(Model model) {
		List<RoleModel> roles = helloWorldService.selectAll();
		DataResult dataResult = new DataResult();
		dataResult.setData(roles);
		return dataResult;
	}
	
	@RequestMapping("helloWorld_save")
	@ResponseBody
	public DataResult insertRole(RoleModel role) {
		DataResult dataResult = new DataResult();
		dataResult.setMsg("success");
		return dataResult;
	}
	
}
