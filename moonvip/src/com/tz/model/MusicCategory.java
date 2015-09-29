/**
 * Tm系统平台
 * moonvip
 * com.tz.model
 * MusicCategory.java
 * 创建人:xuchengfei 
 * 时间：2015年7月28日-下午2:57:21 
 * 2015Tm公司-版权所有
 */
package com.tz.model;

import java.util.Date;

/**
 * 
 * MusicCategory 
 * 创建人:xuchengfei 
 * 时间：2015年7月28日-下午2:57:21
 * 
 * @version 1.0.0
 * 
 */
public class MusicCategory {

	private Integer id;//主键
	private String name;//音乐类型名称
	private Date createTime;//创建时间
	private Integer isDelete;//0未删除1删除
	private Integer sort;//排序号

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
