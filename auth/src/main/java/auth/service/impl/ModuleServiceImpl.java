package auth.service.impl;

import org.springframework.stereotype.Service;

import auth.form.ModuleForm;
import auth.mapper.ModuleMapper;
import auth.model.Module;
import auth.service.ModuleService;
import base.service.impl.BaseServiceImpl;

@Service
public class ModuleServiceImpl extends BaseServiceImpl<Module, ModuleForm, ModuleMapper> implements ModuleService {
	
}
