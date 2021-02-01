/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ysyg.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.ysyg.entity.YUserClothes;

/**
 * 我的衣服DAO接口
 * @author 我的衣服
 * @version 2021-01-28
 */
@MyBatisDao
public interface YUserClothesDao extends CrudDao<YUserClothes> {
	
}