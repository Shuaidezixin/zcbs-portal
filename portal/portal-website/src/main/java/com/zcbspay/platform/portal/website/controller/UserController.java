package com.zcbspay.platform.portal.website.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
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

import net.sf.json.util.JSONUtils;

@Controller
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserController {

	@Autowired
	private UserService userService;
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
		String message = userService.updateUser(userBean);
		Map<String, Object> re = new HashMap<>();
		re.put("info", message);
		return re;
	}

	@ResponseBody
	@RequestMapping("/test")
	public Map<String, Object> saveUser(UserBean userBean, String page, String rows) {
		return userService.queryUsers(userBean, page, rows);
	}
}
