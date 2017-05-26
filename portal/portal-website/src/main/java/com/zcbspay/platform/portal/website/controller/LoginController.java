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
import com.zcbspay.platform.portal.website.util.ConfigParamsUrl;
import com.zcbspay.platform.portal.website.util.CookieUtils;
import com.zcbspay.platform.portal.website.util.HttpRequestParam;
import com.zcbspay.platform.portal.website.util.HttpUtils;
import com.zcbspay.platform.portal.website.util.MD5Util;

import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

@Controller
@RequestMapping("/login")
@SuppressWarnings("all")
public class LoginController {

	// @Autowired
	// private UserService userService;

	@Autowired
	private ConfigParamsUrl configParams;

	/**
	 * 验证用户登录信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/test")
	public void test(UserBean userBean, HttpServletRequest request, String randcode) {
		userBean.setCreateTime("19891212");
		userBean.setCreator("1");
		userBean.setEmail("bema@126.com");
		userBean.setMemberid("121212");
		userBean.setPwd("123");
		userBean.setUserName("bema");
		userBean.setLoginName("bema");
		userBean.setUserId("2");
		// System.out.println(JSONUtils.valueToString(userService.saveUser(userBean)));
		// System.out.println(JSONUtils.valueToString(userService.updateUser(userBean)));
		// System.out.println(JSONUtils.valueToString(userService.queryUsers(userBean,
		// "1", "10")));
		// System.out.println(JSONUtils.valueToString(userService.login(userBean)));
	}

	/**
	 * 验证用户登录信息
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/login")
	public Map<String, Object> validateUser(UserBean user, HttpServletRequest request, HttpServletResponse response) {
		String url = configParams.getUrl("login.login");// "http://localhost:9911/fe/login/login";//
		HttpRequestParam httpRequestParam = new HttpRequestParam("userBeanStr", JSONObject.fromObject(user).toString());
		List<HttpRequestParam> list = new ArrayList<>();
		list.add(httpRequestParam);
		HttpUtils httpUtils = new HttpUtils();
		Map<String, Object> returnmap=null;
		String responseContent = null;
		try {
			httpUtils.openConnection();
			responseContent = httpUtils.executeHttpPost(url, list, Constants.Encoding.UTF_8);
			returnmap= (Map<String, Object>) JSONObject
					.toBean(JSONObject.fromObject(responseContent), Map.class); // userService.login(user);
			if (returnmap.get("code").equals("00")) {
				Cookie cookie = new Cookie(Constants.LoginCanstant.LOGIN_USER_NAME, user.getLoginName());
				cookie.setMaxAge(30 * 60);// 设置为30min
				cookie.setPath("/");
				response.addCookie(cookie);
				Cookie cookielasttime = null;

				HttpRequestParam httpRequestParam1 = new HttpRequestParam("page", "1");
				HttpRequestParam httpRequestParam2 = new HttpRequestParam("rows", "1");
				list.add(httpRequestParam1);
				list.add(httpRequestParam2);
				url = configParams.getUrl("user.queryUsers");// "http://localhost:9911/fe/user/queryUsers";//
				responseContent = httpUtils.executeHttpPost(url, list, Constants.Encoding.UTF_8);
				Map<String, Class> mapClass = new HashMap<String, Class>();
				mapClass.put("rows", Map.class);
				Map<String, Object> userMap = (Map<String, Object>) JSONObject
						.toBean(JSONObject.fromObject(responseContent), Map.class, mapClass);
				if (((List<?>) userMap.get("rows")).get(0) != null) {
					Map<String, Object> re = (Map<String, Object>) ((List<?>) userMap.get("rows")).get(0);
					user.setUserId(re.get("USERID").toString());
					user.setUserName(re.get("USER_NAME").toString());
					user.setPhone(re.get("USER_PHONE") == null ? null : re.get("USER_PHONE").toString());
					user.setErrorTime(re.get("ERROR_TIMES") == null ? null : re.get("ERROR_TIMES").toString());
					user.setEmail(re.get("USER_EMAIL") == null ? null : re.get("USER_EMAIL").toString());
					user.setStatus(re.get("STATUS").toString());
					user.setNotes(re.get("NOTES") == null ? null : re.get("NOTES").toString());

					cookielasttime = new Cookie(Constants.LoginCanstant.LOGIN_LAST_TIME,
							re.get("LAST_LOGINTIME") == null ? "" : re.get("LAST_LOGINTIME").toString());
					cookielasttime.setMaxAge(30 * 60);// 设置为30min
					cookielasttime.setPath("/");
				}
				response.addCookie(cookielasttime);
				request.getSession().setAttribute(Constants.LoginCanstant.LOGIN_USER, user);
			}
		} catch (HttpException e) {
			e.printStackTrace();
		} finally {
			httpUtils.closeConnection();
		}
		return returnmap;
	}

	/**
	 * 用户登出
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/logout")
	public Map<String, Object> logout(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<>();
		HttpSession session = request.getSession(true);
		if (!isNull(session.getAttribute(Constants.LoginCanstant.LOGIN_USER))) {
			session.invalidate();
		}
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals(Constants.LoginCanstant.LOGIN_USER_NAME)) {
					cookie.setMaxAge(0);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			}
		}
		result.put("code", 00);
		result.put("info", "成功");
		return result;
	}

	/**
	 * 用户登出
	 * 
	 * @return
	 */
	@RequestMapping("/loginpage")
	public String loginpage() {
		return "../../login";
	}

	public String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("http_client_ip");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		// 如果是多级代理，那么取第一个ip为客户ip
		if (ip != null && ip.indexOf(",") != -1) {
			ip = ip.substring(ip.lastIndexOf(",") + 1, ip.length()).trim();
		}
		return ip;
	}

	private boolean isNull(Object value) {
		if (value == null || value.toString().equals("")) {
			return true;
		} else {
			return false;
		}

	}

}
