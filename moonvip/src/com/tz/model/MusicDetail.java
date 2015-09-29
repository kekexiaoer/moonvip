/**
 * Tm系统平台
 * moonvip
 * com.tz.model
 * Music.java
 * 创建人:xuchengfei 
 * 时间：2015年6月23日-下午2:02:32 
 * 2015Tm公司-版权所有
 */
package com.tz.model;

import java.util.Date;

/**
 * 
 * Music 创建人:xuchengfei 时间：2015年6月23日-下午2:02:32
 * 
 * @version 1.0.0
 * 
 */
public class MusicDetail implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	private Integer userId;
	private Integer musicId;
	private Date createTime;
	private Integer type;
	private Integer isDelete;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getMusicId() {
		return musicId;
	}

	public void setMusicId(Integer musicId) {
		this.musicId = musicId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

}
