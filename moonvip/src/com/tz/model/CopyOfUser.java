/**
 * Tm系统平台
 * moonvip
 * com.tz.model
 * User.java
 * 创建人:xuchengfei 
 * 时间：2015年6月11日-下午2:06:13 
 * 2015Tm公司-版权所有
 */
package com.tz.model;

import java.util.Date;

/**
 * 
 * User 
 * 创建人:xuchengfei 
 * 时间：2015年6月11日-下午2:06:13
 * 
 * @version 1.0.0
 * 
 */
public class CopyOfUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String account;
	private String telephone;
	private String password;
	private String headerPic;
	private String background;
	private Date createTime;
	private Date updateTime;
	private Integer isDelete;
	private String sign;//签名
	private String company;//公司名称
	private Integer job;//现在的工作 0否1工作
	private Integer age;//年龄
	private Integer male;//性别
	private Integer role;//角色
	/*是否激活邮箱 0未激活 1激活*/
	private Integer active;
	private String code; //临时字段
	
	public CopyOfUser(){
		
	}
	
	public CopyOfUser(Integer id){
		this.id = id;
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getHeaderPic() {
		return headerPic;
	}

	public void setHeaderPic(String headerPic) {
		this.headerPic = headerPic;
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

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getJob() {
		return job;
	}

	public void setJob(Integer job) {
		this.job = job;
	}

	public Integer getMale() {
		return male;
	}

	public void setMale(Integer male) {
		this.male = male;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getBackground() {
		return background;
	}

	public void setBackground(String background) {
		this.background = background;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
}
