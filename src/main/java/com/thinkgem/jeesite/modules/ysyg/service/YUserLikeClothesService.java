/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ysyg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ysyg.entity.YUserLikeClothes;
import com.thinkgem.jeesite.modules.ysyg.dao.YUserLikeClothesDao;

/**
 * 我的搭配Service
 * @author 我的搭配
 * @version 2021-01-28
 */
@Service
@Transactional(readOnly = true)
public class YUserLikeClothesService extends CrudService<YUserLikeClothesDao, YUserLikeClothes> {

	public YUserLikeClothes get(String id) {
		return super.get(id);
	}
	
	public List<YUserLikeClothes> findList(YUserLikeClothes yUserLikeClothes) {
		return super.findList(yUserLikeClothes);
	}
	
	public Page<YUserLikeClothes> findPage(Page<YUserLikeClothes> page, YUserLikeClothes yUserLikeClothes) {
		return super.findPage(page, yUserLikeClothes);
	}
	
	@Transactional(readOnly = false)
	public void save(YUserLikeClothes yUserLikeClothes) {
		super.save(yUserLikeClothes);
	}
	
	@Transactional(readOnly = false)
	public void delete(YUserLikeClothes yUserLikeClothes) {
		super.delete(yUserLikeClothes);
	}
	
}