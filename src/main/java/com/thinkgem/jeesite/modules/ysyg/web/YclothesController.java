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
import com.thinkgem.jeesite.modules.ysyg.entity.Yclothes;
import com.thinkgem.jeesite.modules.ysyg.service.YclothesService;

/**
 * 衣服管理Controller
 * @author 衣服管理
 * @version 2021-01-28
 */
@Controller
@RequestMapping(value = "${adminPath}/ysyg/yclothes")
public class YclothesController extends BaseController {

	@Autowired
	private YclothesService yclothesService;
	
	@ModelAttribute
	public Yclothes get(@RequestParam(required=false) String id) {
		Yclothes entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = yclothesService.get(id);
		}
		if (entity == null){
			entity = new Yclothes();
		}
		return entity;
	}
	
	@RequiresPermissions("ysyg:yclothes:view")
	@RequestMapping(value = {"list", ""})
	public String list(Yclothes yclothes, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Yclothes> page = yclothesService.findPage(new Page<Yclothes>(request, response), yclothes); 
		model.addAttribute("page", page);
		return "modules/ysyg/yclothesList";
	}

	@RequiresPermissions("ysyg:yclothes:view")
	@RequestMapping(value = "form")
	public String form(Yclothes yclothes, Model model) {
		model.addAttribute("yclothes", yclothes);
		return "modules/ysyg/yclothesForm";
	}

	@RequiresPermissions("ysyg:yclothes:edit")
	@RequestMapping(value = "save")
	public String save(Yclothes yclothes, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, yclothes)){
			return form(yclothes, model);
		}
		yclothesService.save(yclothes);
		addMessage(redirectAttributes, "保存衣服管理成功");
		return "redirect:"+Global.getAdminPath()+"/ysyg/yclothes/?repage";
	}
	
	@RequiresPermissions("ysyg:yclothes:edit")
	@RequestMapping(value = "delete")
	public String delete(Yclothes yclothes, RedirectAttributes redirectAttributes) {
		yclothesService.delete(yclothes);
		addMessage(redirectAttributes, "删除衣服管理成功");
		return "redirect:"+Global.getAdminPath()+"/ysyg/yclothes/?repage";
	}

}