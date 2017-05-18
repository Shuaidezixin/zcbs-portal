package com.zcbspay.platform.portal.website.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;
import com.zcbspay.platform.portal.query.statistics.service.QueryAndStatService;
import com.zcbspay.platform.portal.system.bean.UserBean;
import com.zcbspay.platform.portal.website.constant.Constants;
import com.zcbspay.platform.portal.website.util.ConfigParams;
import com.zcbspay.platform.portal.website.util.HttpRequestParam;
import com.zcbspay.platform.portal.website.util.HttpUtils;
import com.zcbspay.platform.portal.website.util.UserHelper;

import net.sf.json.JSONObject;

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
	

	//@Autowired
	//private QueryAndStatService queryAndStatService;
	@Autowired
	private ConfigParams configParams;
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
	public String orderForBatchAndSingle(String page, String rows, TxnsForPortalBean txnsForPortalBean,HttpServletRequest request) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		//return queryAndStatService.orderForBatchAndSingle(page, rows, txnsForPortalBean);
		
		String url=configParams.getUrls().get("basepath")+configParams.getUrls().get("order.batchAndSingle");//"http://localhost:9911/fe/order/orderForBatchAndSingle";//
		
		HttpRequestParam httpRequestParam= new HttpRequestParam("txnsForPortalBeanStr",JSONObject.fromObject(txnsForPortalBean).toString());
		HttpRequestParam httpRequestParam1= new HttpRequestParam("page",page);
	    HttpRequestParam httpRequestParam2= new HttpRequestParam("rows",rows);
			
		List<HttpRequestParam> list = new ArrayList<>();
		list.add(httpRequestParam1);
		list.add(httpRequestParam2);
		list.add(httpRequestParam);
		
		HttpUtils httpUtils = new HttpUtils();
		httpUtils.openConnection();
		String responseContent=null;
		try {
			 responseContent = httpUtils.executeHttpPost(url,list,Constants.Encoding.UTF_8);
		} catch (HttpException e) {
			e.printStackTrace();
		}
		httpUtils.closeConnection();
		Map<String, Class> mapClass=new HashMap<String,Class>();
		mapClass.put("rows", Map.class);
		Map<String, Object> map= (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(responseContent),Map.class,mapClass);
		return JSONObject.fromObject(map).toString();
	}
}
