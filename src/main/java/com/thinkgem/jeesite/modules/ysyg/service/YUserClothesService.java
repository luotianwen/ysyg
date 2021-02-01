/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ysyg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ysyg.entity.YUserClothes;
import com.thinkgem.jeesite.modules.ysyg.dao.YUserClothesDao;

/**
 * 我的衣服Service
 * @author 我的衣服
 * @version 2021-01-28
 */
@Service
@Transactional(readOnly = true)
public class YUserClothesService extends CrudService<YUserClothesDao, YUserClothes> {

	public YUserClothes get(String id) {
		return super.get(id);
	}
	
	public List<YUserClothes> findList(YUserClothes yUserClothes) {
		return super.findList(yUserClothes);
	}
	
	public Page<YUserClothes> findPage(Page<YUserClothes> page, YUserClothes yUserClothes) {
		return super.findPage(page, yUserClothes);
	}
	
	@Transactional(readOnly = false)
	public void save(YUserClothes yUserClothes) {
		super.save(yUserClothes);
	}
	
	@Transactional(readOnly = false)
	public void delete(YUserClothes yUserClothes) {
		super.delete(yUserClothes);
	}
	
}