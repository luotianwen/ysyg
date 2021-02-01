/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ysyg.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.ysyg.entity.Yuser;
import com.thinkgem.jeesite.modules.ysyg.dao.YuserDao;

/**
 * 用户管理Service
 * @author 用户管理
 * @version 2021-01-28
 */
@Service
@Transactional(readOnly = true)
public class YuserService extends CrudService<YuserDao, Yuser> {

	public Yuser get(String id) {
		return super.get(id);
	}
	
	public List<Yuser> findList(Yuser yuser) {
		return super.findList(yuser);
	}
	
	public Page<Yuser> findPage(Page<Yuser> page, Yuser yuser) {
		return super.findPage(page, yuser);
	}
	
	@Transactional(readOnly = false)
	public void save(Yuser yuser) {
		super.save(yuser);
	}
	
	@Transactional(readOnly = false)
	public void delete(Yuser yuser) {
		super.delete(yuser);
	}
	
}