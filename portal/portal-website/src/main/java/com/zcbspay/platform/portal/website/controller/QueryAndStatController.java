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

import com.zcbspay.platform.manager.trade.bean.TxnsForPortalBean;
import com.zcbspay.platform.portal.query.statistics.service.QueryAndStatService;
import com.zcbspay.platform.portal.system.bean.UserBean;
import com.zcbspay.platform.portal.system.service.UserService;
import com.zcbspay.platform.portal.website.constant.Constants;
import com.zcbspay.platform.portal.website.util.CookieUtils;
import com.zcbspay.platform.portal.website.util.MD5Util;

import net.sf.json.util.JSONUtils;

@Controller
@RequestMapping("/queryAndStat")
@SuppressWarnings("all")
public class QueryAndStatController {

    @Autowired
	private QueryAndStatService queryAndStatService;
    
    /**
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/test")
	public void test(TxnsForPortalBean txnsForPortalBean,HttpServletRequest request,String randcode) {
		Map<String, Object> map = queryAndStatService.queryTxnsDeta("1", "10", txnsForPortalBean);
		
		System.out.println(JSONUtils.valueToString(map));
	}
}
