package com.zcbspay.platform.portal.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.portal.system.bean.UserBean;
import com.zcbspay.platform.portal.system.dao.UserDao;
import com.zcbspay.platform.portal.system.service.UserService;
@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;

	@Override
	@Transactional
	public Map<String, Object> login(UserBean userbean) {
		String reString=userDao.login(userbean);
		String[] reArray=reString.split(",");
		Map<String, Object> resMap=new HashMap<>();
		if (reArray[0].equals("00")) {
			resMap.put("result", "success");
		}else{
			resMap.put("result", "error");
		}
		resMap.put("code", reArray[0]);
		resMap.put("info", reArray[1]);
		return resMap;
		
	}

	@Override
	public Map<String, Object> queryUsers(UserBean userBean, String page, String rows) {
		Map<String, Object> userList= userDao.queryUsers(userBean,page,rows);
		return userList;
	}

	@Override
	public String saveUser(UserBean userBean) {
		return userDao.saveUser(userBean);
	}

	@Override
	public Map<String, Object> updateUser(UserBean userBean) {
		return userDao.updateUser(userBean);
	}
	
}
