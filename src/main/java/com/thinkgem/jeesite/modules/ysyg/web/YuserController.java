/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ysyg.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.ysyg.entity.Yuser;
import com.thinkgem.jeesite.modules.ysyg.service.YuserService;

/**
 * 用户管理Controller
 * @author 用户管理
 * @version 2021-01-28
 */
@Controller
@RequestMapping(value = "${adminPath}/ysyg/yuser")
public class YuserController extends BaseController {

	@Autowired
	private YuserService yuserService;
	
	@ModelAttribute
	public Yuser get(@RequestParam(required=false) String id) {
		Yuser entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = yuserService.get(id);
		}
		if (entity == null){
			entity = new Yuser();
		}
		return entity;
	}
	
	@RequiresPermissions("ysyg:yuser:view")
	@RequestMapping(value = {"list", ""})
	public String list(Yuser yuser, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Yuser> page = yuserService.findPage(new Page<Yuser>(request, response), yuser); 
		model.addAttribute("page", page);
		return "modules/ysyg/yuserList";
	}

	@RequiresPermissions("ysyg:yuser:view")
	@RequestMapping(value = "form")
	public String form(Yuser yuser, Model model) {
		model.addAttribute("yuser", yuser);
		return "modules/ysyg/yuserForm";
	}

	@RequiresPermissions("ysyg:yuser:edit")
	@RequestMapping(value = "save")
	public String save(Yuser yuser, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, yuser)){
			return form(yuser, model);
		}
		yuserService.save(yuser);
		addMessage(redirectAttributes, "保存用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/ysyg/yuser/?repage";
	}
	
	@RequiresPermissions("ysyg:yuser:edit")
	@RequestMapping(value = "delete")
	public String delete(Yuser yuser, RedirectAttributes redirectAttributes) {
		yuserService.delete(yuser);
		addMessage(redirectAttributes, "删除用户管理成功");
		return "redirect:"+Global.getAdminPath()+"/ysyg/yuser/?repage";
	}

}