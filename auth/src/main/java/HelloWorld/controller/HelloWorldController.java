package HelloWorld.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;

import HelloWorld.form.RoleFrom;
import HelloWorld.model.RoleModel;
import HelloWorld.service.HelloWorldService;
import base.result.DataResult;

@Controller
public class HelloWorldController {

	@Autowired
	HelloWorldService helloWorldService;
	
	@RequestMapping("helloWorld_select")
	@ResponseBody
	public DataResult selectById(Long id) {
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
	public DataResult selectById(RoleFrom roleFrom) {
		PageInfo<RoleModel> roles = helloWorldService.selectAll(roleFrom);
		DataResult dataResult = new DataResult();
		dataResult.setData(roles);
		return dataResult;
	}
	
	@RequestMapping("helloWorld_save")
	public String insertRole(RoleModel role) {
		RoleModel roleModel = new RoleModel();
		roleModel.setName("test" + new Date().toString());
		helloWorldService.insert(roleModel);
		DataResult dataResult = new DataResult();
		dataResult.setMsg("success");
		return "redirect:/helloWorld_selectAll";
	}
	
}
