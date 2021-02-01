/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.ysyg.entity;

import com.thinkgem.jeesite.modules.sys.entity.User;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 我的搭配Entity
 * @author 我的搭配
 * @version 2021-01-28
 */
public class YUserLikeClothes extends DataEntity<YUserLikeClothes> {
	
	private static final long serialVersionUID = 1L;
	private Yuser user;		// 用户
	private Yclothes cid;		// 衣服
	private String score;		// 权重
	private String note;		// 天气
	
	public YUserLikeClothes() {
		super();
	}

	public YUserLikeClothes(String id){
		super(id);
	}

	@NotNull(message="用户不能为空")
	public Yuser getUser() {
		return user;
	}

	public void setUser(Yuser user) {
		this.user = user;
	}
	
	@NotNull(message="衣服不能为空")
	public Yclothes getCid() {
		return cid;
	}

	public void setCid(Yclothes cId) {
		this.cid = cId;
	}
	
	@Length(min=0, max=32, message="权重长度必须介于 0 和 32 之间")
	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}
	
	@Length(min=0, max=255, message="天气长度必须介于 0 和 255 之间")
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
}