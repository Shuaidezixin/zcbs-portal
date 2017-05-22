package com.zcbspay.platform.portal.system.service;

import java.util.Map;

import com.zcbspay.platform.portal.system.bean.UserBean;

public interface UserService{
	public Map<String, Object> login(UserBean userbean);
	
	public Map<String, Object> queryUsers(UserBean userBean,String page,String rows);
	
	public Map<String, Object> saveUser(UserBean userBean);
	
	public Map<String, Object> updateUser(UserBean userBean);
	
}
