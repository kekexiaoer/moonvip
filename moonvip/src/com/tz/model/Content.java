/**
 * tzdesk系统平台
 * keke_dialog
 * com.tz.bean
 * Content.java
 * 创建人:xuchengfei 
 * 时间：2015年10月4日-下午9:22:57 
 * 2015潭州教育公司-版权所有
 */
package com.tz.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

/**
 * 
 * Content 创建人:xuchengfei 时间：2015年10月4日-下午9:22:57
 * 
 * @version 1.0.0
 * 
 */
@Entity
@Table(name = "tz_content")
public class Content implements java.io.Serializable {
	private Integer id;// 主键
	private Integer channelId;// 栏目ID
	private String title;// 标题
	private String description;// 描述
	private String content;// 内容
	private String img;// 图片
	private String author;// 作者
	private String keywords;// 关键字
	private Integer hits;// 点击数
	private Integer commentHits;// 评论数
	private Integer loves;
	private Date createTime;// 创建时间
	private Date updateTime;// 更新时间
	private Integer isDelete;// 删除状态0 未删除1删除
	private Integer status;// 发布状态0未发布1发布

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "channel_id")
	public Integer getChannelId() {
		return channelId;
	}

	public void setChannelId(Integer channelId) {
		this.channelId = channelId;
	}

	@Column(name = "title")
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "description")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Lob
	@Column(name = "content")
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "img")
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	@Column(name = "author")
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Column(name = "keywords")
	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	@Column(name = "hits")
	public Integer getHits() {
		return hits;
	}

	public void setHits(Integer hits) {
		this.hits = hits;
	}

	@Column(name = "comment_hits")
	public Integer getCommentHits() {
		return commentHits;
	}

	public void setCommentHits(Integer commentHits) {
		this.commentHits = commentHits;
	}

	@Column(name = "loves")
	public Integer getLoves() {
		return loves;
	}

	public void setLoves(Integer loves) {
		this.loves = loves;
	}

	@Column(name = "create_time")
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time")
	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "is_delete")
	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	@Column(name = "status")
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}