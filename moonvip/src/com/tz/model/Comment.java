/**
 * Tm系统平台
 * moonvip
 * com.tz.model
 * Content.java
 * 创建人:xuchengfei 
 * 时间：2015年6月8日-下午1:13:00 
 * 2015Tm公司-版权所有
 */
package com.tz.model;

import java.util.Date;

/**
 * 
 * Content 创建人:xuchengfei 时间：2015年6月8日-下午1:13:00
 * 
 * @version 1.0.0
 * 
 */
public class Comment implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;/* 主键 */
	private String content;// 内容
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private Integer userId;// 用户ID
	private Integer isTop;// 是否置顶1置顶0未置顶
	private Integer isDelete;// 0删除1未删除
	private Integer status;// 0未发布 1发布 2加黑 
	private String ip;
	private Integer contentId;//内容
	private Integer parentId;//回复的ID是

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getIsTop() {
		return isTop;
	}

	public void setIsTop(Integer isTop) {
		this.isTop = isTop;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getContentId() {
		return contentId;
	}

	public void setContentId(Integer contentId) {
		this.contentId = contentId;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
}
