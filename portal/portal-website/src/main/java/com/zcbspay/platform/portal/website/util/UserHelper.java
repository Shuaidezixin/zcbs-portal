package com.zcbspay.platform.portal.website.util;

import javax.servlet.http.HttpServletRequest;

import com.zcbspay.platform.portal.system.bean.UserBean;
import com.zcbspay.platform.portal.website.constant.Constants;

public class UserHelper {

	public static UserBean getCurrentUser(HttpServletRequest request) {
		Object object=request.getSession().getAttribute(Constants.LoginCanstant.LOGIN_USER);
		if (object==null) {
			UserBean userBean=new UserBean();
			userBean.setMemberid("200000000000610");
			return userBean;
		}else{
			return (UserBean)object ;
		}
	}
}
