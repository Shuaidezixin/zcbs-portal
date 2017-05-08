package com.zcbspay.platform.portal.website.util;

import javax.servlet.http.HttpServletRequest;

import com.zcbspay.platform.portal.system.bean.UserBean;
import com.zcbspay.platform.portal.website.constant.Constants;

public class UserHelper {

	public static UserBean getCurrentUser(HttpServletRequest request) {
		return (UserBean) request.getSession().getAttribute(Constants.LoginCanstant.LOGIN_USER);
	}
}
