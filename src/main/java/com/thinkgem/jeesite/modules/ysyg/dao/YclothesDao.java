/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ysyg.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ysyg.entity.Yclothes;

/**
 * 衣服管理DAO接口
 * @author 衣服管理
 * @version 2021-01-28
 */
@MyBatisDao
public interface YclothesDao extends CrudDao<Yclothes> {
	
}