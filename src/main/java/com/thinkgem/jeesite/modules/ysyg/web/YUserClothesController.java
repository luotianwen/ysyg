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
import com.thinkgem.jeesite.modules.ysyg.entity.YUserClothes;
import com.thinkgem.jeesite.modules.ysyg.service.YUserClothesService;

/**
 * 我的衣服Controller
 * @author 我的衣服
 * @version 2021-01-28
 */
@Controller
@RequestMapping(value = "${adminPath}/ysyg/yUserClothes")
public class YUserClothesController extends BaseController {

	@Autowired
	private YUserClothesService yUserClothesService;
	
	@ModelAttribute
	public YUserClothes get(@RequestParam(required=false) String id) {
		YUserClothes entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = yUserClothesService.get(id);
		}
		if (entity == null){
			entity = new YUserClothes();
		}
		return entity;
	}
	
	@RequiresPermissions("ysyg:yUserClothes:view")
	@RequestMapping(value = {"list", ""})
	public String list(YUserClothes yUserClothes, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YUserClothes> page = yUserClothesService.findPage(new Page<YUserClothes>(request, response), yUserClothes); 
		model.addAttribute("page", page);
		return "modules/ysyg/yUserClothesList";
	}

	@RequiresPermissions("ysyg:yUserClothes:view")
	@RequestMapping(value = "form")
	public String form(YUserClothes yUserClothes, Model model) {
		model.addAttribute("yUserClothes", yUserClothes);
		return "modules/ysyg/yUserClothesForm";
	}

	@RequiresPermissions("ysyg:yUserClothes:edit")
	@RequestMapping(value = "save")
	public String save(YUserClothes yUserClothes, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, yUserClothes)){
			return form(yUserClothes, model);
		}
		yUserClothesService.save(yUserClothes);
		addMessage(redirectAttributes, "保存我的衣服成功");
		return "redirect:"+Global.getAdminPath()+"/ysyg/yUserClothes/?repage";
	}
	
	@RequiresPermissions("ysyg:yUserClothes:edit")
	@RequestMapping(value = "delete")
	public String delete(YUserClothes yUserClothes, RedirectAttributes redirectAttributes) {
		yUserClothesService.delete(yUserClothes);
		addMessage(redirectAttributes, "删除我的衣服成功");
		return "redirect:"+Global.getAdminPath()+"/ysyg/yUserClothes/?repage";
	}

}