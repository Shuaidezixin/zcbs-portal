package com.zcbspay.platform.portal.system.api.service;

import java.util.Map;

import com.zcbspay.platform.portal.system.api.bean.UserBean;

public interface UserService{
	public String login(UserBean userbean);
	
	public Map<String, Object> queryUsers(UserBean userBean,String page,String rows);
	
	public String saveUser(UserBean userBean);
	
	public String updateUser(UserBean userBean);
	
}
