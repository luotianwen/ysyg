/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ysyg.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 我的衣服Entity
 * @author 我的衣服
 * @version 2021-01-28
 */
public class YUserClothes extends DataEntity<YUserClothes> {
	
	private static final long serialVersionUID = 1L;
	private Yuser user;		// 用户
	private Yclothes c;		// 衣服
	
	public YUserClothes() {
		super();
	}

	public YUserClothes(String id){
		super(id);
	}

	public Yuser getUser() {
		return user;
	}

	public void setUser(Yuser user) {
		this.user = user;
	}
	
	public Yclothes getC() {
		return c;
	}

	public void setC(Yclothes c) {
		this.c = c;
	}
	
}