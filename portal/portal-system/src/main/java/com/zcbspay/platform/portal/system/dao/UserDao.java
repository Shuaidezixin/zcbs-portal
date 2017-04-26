package com.zcbspay.platform.portal.system.dao;

import java.util.Map;

import com.zcbspay.platform.portal.common.dao.BaseDAO;
import com.zcbspay.platform.portal.system.api.bean.UserBean;

public interface UserDao extends BaseDAO<String>{

	Map<String, Object> queryUsers(UserBean userbean, String page, String rows);

	String saveUser(UserBean userBean);

	String updateUser(UserBean userBean);

	String login(UserBean userbean);
	
}
