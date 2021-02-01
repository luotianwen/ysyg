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
import com.thinkgem.jeesite.modules.ysyg.entity.YUserLikeClothes;
import com.thinkgem.jeesite.modules.ysyg.service.YUserLikeClothesService;

/**
 * 我的搭配Controller
 * @author 我的搭配
 * @version 2021-01-28
 */
@Controller
@RequestMapping(value = "${adminPath}/ysyg/yUserLikeClothes")
public class YUserLikeClothesController extends BaseController {

	@Autowired
	private YUserLikeClothesService yUserLikeClothesService;
	
	@ModelAttribute
	public YUserLikeClothes get(@RequestParam(required=false) String id) {
		YUserLikeClothes entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = yUserLikeClothesService.get(id);
		}
		if (entity == null){
			entity = new YUserLikeClothes();
		}
		return entity;
	}
	
	@RequiresPermissions("ysyg:yUserLikeClothes:view")
	@RequestMapping(value = {"list", ""})
	public String list(YUserLikeClothes yUserLikeClothes, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<YUserLikeClothes> page = yUserLikeClothesService.findPage(new Page<YUserLikeClothes>(request, response), yUserLikeClothes); 
		model.addAttribute("page", page);
		return "modules/ysyg/yUserLikeClothesList";
	}

	@RequiresPermissions("ysyg:yUserLikeClothes:view")
	@RequestMapping(value = "form")
	public String form(YUserLikeClothes yUserLikeClothes, Model model) {
		model.addAttribute("yUserLikeClothes", yUserLikeClothes);
		return "modules/ysyg/yUserLikeClothesForm";
	}

	@RequiresPermissions("ysyg:yUserLikeClothes:edit")
	@RequestMapping(value = "save")
	public String save(YUserLikeClothes yUserLikeClothes, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, yUserLikeClothes)){
			return form(yUserLikeClothes, model);
		}
		yUserLikeClothesService.save(yUserLikeClothes);
		addMessage(redirectAttributes, "保存我的搭配成功");
		return "redirect:"+Global.getAdminPath()+"/ysyg/yUserLikeClothes/?repage";
	}
	
	@RequiresPermissions("ysyg:yUserLikeClothes:edit")
	@RequestMapping(value = "delete")
	public String delete(YUserLikeClothes yUserLikeClothes, RedirectAttributes redirectAttributes) {
		yUserLikeClothesService.delete(yUserLikeClothes);
		addMessage(redirectAttributes, "删除我的搭配成功");
		return "redirect:"+Global.getAdminPath()+"/ysyg/yUserLikeClothes/?repage";
	}

}