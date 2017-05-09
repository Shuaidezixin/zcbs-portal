package com.zcbspay.platform.portal.website.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;
import com.zcbspay.platform.portal.query.statistics.service.QueryAndStatService;
import com.zcbspay.platform.portal.system.bean.UserBean;
import com.zcbspay.platform.portal.website.util.UserHelper;

/**
 * 交易报表模块
 * 
 * @author: zhangshd
 * @date: 2017年5月2日 下午2:36:04
 * @version :v1.0
 */
@Controller
@RequestMapping("/order")
@SuppressWarnings("all")
public class OrderController {

	@Autowired
	private QueryAndStatService queryAndStatService;
	
	/**
	 * 订单查询 页面
	 * @author: zhangshd
	 * @return String
	 * @date: 2017年5月9日 上午9:41:25 
	 * @version v1.0
	 */
	@RequestMapping("showOrder")
	public String showOrder() {
		return "order/order";
	}
	/**
	 * 订单查询
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @param request
	 * @return Map<String,Object>
	 * @date: 2017年5月9日 上午9:41:45 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/orderForBatchAndSingle")
	public Map<String, Object> orderForBatchAndSingle(String page, String rows, TxnsForPortalBean txnsForPortalBean,HttpServletRequest request) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		return queryAndStatService.orderForBatchAndSingle(page, rows, txnsForPortalBean);
	}
}
