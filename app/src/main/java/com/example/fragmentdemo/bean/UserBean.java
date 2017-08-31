package com.example.fragmentdemo.bean;

import cn.bmob.v3.BmobObject;

/**
 * 用户信息实体类
 * @author 
 *
 */
public class UserBean extends BmobObject {
	private static final long serialVersionUID = 762796567708784658L;
	
	private String userId;
	private String password;
	private String userName;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
