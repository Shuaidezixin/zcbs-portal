package com.zcbspay.platform.portal.website.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcbspay.platform.portal.system.bean.UserBean;
import com.zcbspay.platform.portal.system.service.UserService;
import com.zcbspay.platform.portal.website.constant.Constants;
import com.zcbspay.platform.portal.website.util.CookieUtils;
import com.zcbspay.platform.portal.website.util.MD5Util;
import com.zcbspay.platform.portal.website.util.UserHelper;

import net.sf.json.util.JSONUtils;

@Controller
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserController {

	@Autowired
	private UserService userService;
	
	@RequestMapping("/showUserManager")
	public String showBusiRate() {
		return "user/userManager";
	}
	
	@RequestMapping("/toModifyUserPwd")
	public String toModifyUserPwd() {
		return "user/modify_loginpwd";
	}
	
	@ResponseBody
	@RequestMapping("/queryUsers")
	public Map<String, Object> queryUsers(UserBean userBean, String page, String rows) {
		return userService.queryUsers(userBean, page, rows);
	}
	
	@ResponseBody
	@RequestMapping("/saveUser")
	public Map<String, Object> saveUser(UserBean userBean) {
		String message = userService.saveUser(userBean);
		Map<String, Object> re = new HashMap<>();
		re.put("info", message);
		return re;
	}

	@ResponseBody
	@RequestMapping("/updateUser")
	public Map<String, Object> updateUser(UserBean userBean) {
		Map<String, Object> message = userService.updateUser(userBean);
		return message;
	}
	/**
	 * 
	 * @author: zhangshd
	 * @param userBean
	 * @return Map<String,Object>
	 * @date: 2017年5月9日 下午1:28:44 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/modifyPwd")
	public Map<String, Object> modifyPwd(String password,String passwordnew,String confirm_passwordnew,HttpServletRequest request) {
		UserBean userBean=new UserBean();
		UserBean loginUser=UserHelper.getCurrentUser(request);
		userBean.setPwd(password);
		userBean.setMemberid(loginUser.getMemberid());
		userBean.setLoginName(loginUser.getLoginName());
		Map<String, Object> returnMap = userService.login(userBean);
		Map<String, Object> message =  new HashMap<>();
		if (returnMap.get("code").equals("00")) {
			loginUser.setPwd(passwordnew);
			message= userService.updateUser(loginUser);
		}else{
			message.put("RET", "error");
			message.put("INFO","更新失败");
		}
		return message;
	}

	@ResponseBody
	@RequestMapping("/test")
	public Map<String, Object> saveUser(UserBean userBean, String page, String rows) {
		return userService.queryUsers(userBean, "1", "10");
	}
}
