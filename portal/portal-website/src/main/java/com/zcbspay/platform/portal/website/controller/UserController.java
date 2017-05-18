package com.zcbspay.platform.portal.website.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcbspay.platform.portal.system.bean.UserBean;
import com.zcbspay.platform.portal.system.service.UserService;
import com.zcbspay.platform.portal.website.constant.Constants;
import com.zcbspay.platform.portal.website.util.ConfigParams;
import com.zcbspay.platform.portal.website.util.CookieUtils;
import com.zcbspay.platform.portal.website.util.HttpRequestParam;
import com.zcbspay.platform.portal.website.util.HttpUtils;
import com.zcbspay.platform.portal.website.util.MD5Util;
import com.zcbspay.platform.portal.website.util.UserHelper;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

@Controller
@RequestMapping("/user")
@SuppressWarnings("all")
public class UserController {

	//@Autowired
	private UserService userService;

	@Autowired
	private ConfigParams configParams;
	
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
	public String queryUsers(UserBean userBean, String page, String rows) {
		String url=configParams.getUrls().get("basepath")+configParams.getUrls().get("user.queryUsers");//"http://localhost:9911/fe/user/queryUsers";//user.queryUsers
		HttpRequestParam httpRequestParam= new HttpRequestParam("userBeanStr",JSONObject.fromObject(userBean).toString());
		HttpRequestParam httpRequestParam1= new HttpRequestParam("page",page);
		HttpRequestParam httpRequestParam2= new HttpRequestParam("rows",rows);
		List<HttpRequestParam> list = new ArrayList<>();
		list.add(httpRequestParam);
		list.add(httpRequestParam1);
		list.add(httpRequestParam2);
		
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.openConnection();
		String responseContent=null;
		try {
			 responseContent = httpUtils.executeHttpPost(url,list,Constants.Encoding.UTF_8);
		} catch (HttpException e) {
			e.printStackTrace();
		}
		httpUtils.closeConnection();
		Map<String, Class> mapClass = new HashMap<String, Class>();
		mapClass.put("rows", Map.class);
		Map<String, Object> map = (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(responseContent),
				Map.class, mapClass);
		return JSONObject.fromObject(map).toString();
		//return userService.queryUsers(userBean, page, rows);
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
	public Map<String, Object> modifyPwd(String password, String passwordnew, String confirm_passwordnew,
			HttpServletRequest request) {
		String LoginUrl =configParams.getUrls().get("basepath")+configParams.getUrls().get("login.login");// "http://localhost:9911/fe/login/login";
		String modifyPwd =configParams.getUrls().get("basepath")+configParams.getUrls().get("user.modifyPwd");// "http://localhost:9911/fe/user/modifyPwd";//

		UserBean userBean = new UserBean();
		UserBean loginUser = UserHelper.getCurrentUser(request);
		userBean.setPwd(password);
		userBean.setMemberid(loginUser.getMemberid());
		userBean.setLoginName(loginUser.getLoginName());

		HttpRequestParam httpRequestParam = new HttpRequestParam("user",
				JSONObject.fromObject(userBean).toString());
		List<HttpRequestParam> list = new ArrayList<>();
		list.add(httpRequestParam);

		HttpUtils httpUtils = new HttpUtils();
		httpUtils.openConnection();
		String responseContent = null;
		Map<String, Object> message = new HashMap<>();
		try {
			responseContent = httpUtils.executeHttpPost(LoginUrl, list, Constants.Encoding.UTF_8);

			Map<String, Object> returnMap = (Map<String, Object>) JSONObject
					.toBean(JSONObject.fromObject(responseContent), Map.class);
			
			if (returnMap.get("code").equals("00")) {
				loginUser.setPwd(passwordnew);
				httpRequestParam = new HttpRequestParam("userBeanStr", JSONObject.fromObject(loginUser).toString());
				list.clear();
				list.add(httpRequestParam);

				responseContent = httpUtils.executeHttpPost(LoginUrl, list, Constants.Encoding.UTF_8);
				message =(Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(responseContent),Map.class);
			} else {
				message.put("RET", "error");
				message.put("INFO", "更新失败");
			}

		} catch (HttpException e) {
			e.printStackTrace();
		} finally {
			httpUtils.closeConnection();
		}

		return message;
	}

	@ResponseBody
	@RequestMapping("/test")
	public Map<String, Object> saveUser(UserBean userBean, String page, String rows) {
		return userService.queryUsers(userBean, "1", "10");
	}
}
