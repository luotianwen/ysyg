/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ysyg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ysyg.entity.Yclothes;
import com.thinkgem.jeesite.modules.ysyg.dao.YclothesDao;

/**
 * 衣服管理Service
 * @author 衣服管理
 * @version 2021-01-28
 */
@Service
@Transactional(readOnly = true)
public class YclothesService extends CrudService<YclothesDao, Yclothes> {

	public Yclothes get(String id) {
		return super.get(id);
	}
	
	public List<Yclothes> findList(Yclothes yclothes) {
		return super.findList(yclothes);
	}
	
	public Page<Yclothes> findPage(Page<Yclothes> page, Yclothes yclothes) {
		return super.findPage(page, yclothes);
	}
	
	@Transactional(readOnly = false)
	public void save(Yclothes yclothes) {
		super.save(yclothes);
	}
	
	@Transactional(readOnly = false)
	public void delete(Yclothes yclothes) {
		super.delete(yclothes);
	}
	
}