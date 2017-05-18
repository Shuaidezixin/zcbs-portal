package com.zcbspay.platform.portal.website.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;
import com.zcbspay.platform.portal.query.statistics.service.QueryAndStatService;
import com.zcbspay.platform.portal.website.constant.PathConstants;
import com.zcbspay.platform.portal.website.util.ConfigParams;
import com.zcbspay.platform.portal.website.util.HttpUtils;
import com.zcbspay.platform.portal.website.util.JSONUtils;
import com.zcbspay.platform.portal.website.util.UserHelper;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 交易查询模块
 * 
 * @author: zhangshd
 * @date: 2017年5月2日 下午2:35:49
 * @version :v1.0
 */
@Controller
@RequestMapping("/trade")
@SuppressWarnings("all")
public class TradeQueryController {
	private static final Logger logger = LoggerFactory.getLogger(TradeQueryController.class);
	
	@Autowired
	private ConfigParams configParams;

	@Autowired
	private QueryAndStatService tradeService;

	@ResponseBody
	@RequestMapping("/showQueryTrade")
	public ModelAndView showQueryTrade(String busicode) {
		ModelAndView modelAndView = new ModelAndView("trade/queryTrade");
		if (busicode == null) {
			busicode = "";
		}
		modelAndView.addObject("busicode", busicode);
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping("/showQueryBatchTrade")
	public ModelAndView showQueryBatchTrade(String busicode) {
		ModelAndView modelAndView = new ModelAndView("trade/queryBatchTrade");
		if (busicode == null) {
			busicode = "";
		}
		modelAndView.addObject("busicode", busicode);
		return modelAndView;
	}

	@RequestMapping("/showQueryTradeDetail")
	public String showQueryTradeDetail() {
		return "trade/queryTradeDetail";
	}

	/**
	 * 交易查询 批量和实时类交易分别对应两种表头，具体划分为批量代收付和实时代收付四种交易类型pck_sel_txns_single
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:23:35
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping(value="/tradeQuery", produces = "application/json;charset=UTF-8")
	public String tradeQuery(String page, String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		HttpUtils httpUtils = new HttpUtils();
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("data", JSONObject.fromObject(txnsForPortalBean).toString());
		try {
			httpUtils.openConnection();
			String result = httpUtils.post(configParams.getUrl("trade.selTxnsSingleUrl"), paramMap);
			Map<String, Class> mapClass=new HashMap<String,Class>();
			mapClass.put("rows", Map.class);
			Map<String, Object> map= (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(result),Map.class,mapClass);
			return JSONObject.fromObject(map).toString();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("服务异常！");
			Map<String,Object> mapRet = new HashMap<>();
			Map<String,Object> mapErr = new HashMap<>();
			mapErr.put("RET", "err");
			mapErr.put("INFO", "服务异常！");
			mapRet.put("tatal", 0);
			mapRet.put("rows", mapErr);
			return JSONObject.fromObject(mapRet).toString();
		}finally {
			httpUtils.closeConnection();
		}
	}

	/**
	 * 批次明细查询 pck_sel_txns_deta
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:23:40
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping(value="/tradeQueryForBatch", produces = "application/json;charset=UTF-8")
	public String tradeQueryForBatch(@RequestParam(defaultValue = "1") String page,
			@RequestParam(defaultValue = "10") String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		//String url = "http://192.168.2.130:8100/fe/trade/selTxnsDeta";
		HttpUtils httpUtils = new HttpUtils();
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("data", JSONObject.fromObject(txnsForPortalBean).toString());
		try {
			httpUtils.openConnection();
			String result = httpUtils.post(configParams.getUrl("trade.selTxnsDetaUrl"), paramMap);
			Map<String, Class> mapClass=new HashMap<String,Class>();
			mapClass.put("rows", Map.class);
			Map<String, Object> map= (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(result),Map.class,mapClass);
			return JSONObject.fromObject(map).toString();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("服务异常！");
			Map<String,Object> mapRet = new HashMap<>();
			Map<String,Object> mapErr = new HashMap<>();
			mapErr.put("RET", "err");
			mapErr.put("INFO", "服务异常！");
			mapRet.put("tatal", 0);
			mapRet.put("rows", mapErr);
			return JSONObject.fromObject(mapRet).toString();
		}finally {
			httpUtils.closeConnection();
		}
	}

	/**
	 * 跳到批次明细查询 pck_sel_txns_deta
	 * 
	 * @author: 张连海
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return modelAndView
	 * @date: 2017年5月2日 下午2:23:40
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping(value="/toTradeQueryForBatch",produces = "application/json;charset=UTF-8")
	public ModelAndView toTradeQueryForBatch(@RequestParam(defaultValue = "1") String page,
			@RequestParam(defaultValue = "10") String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/trade/queryBatchTradeDetail");
		modelAndView.addObject("batchno", txnsForPortalBean.getBatchno());
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		//String url = "http://192.168.2.130:8100/fe/trade/selTxnsDeta";
		HttpUtils httpUtils = new HttpUtils();
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("data", JSONObject.fromObject(txnsForPortalBean).toString());
		try {
			httpUtils.openConnection();
			String result = httpUtils.post(configParams.getUrl("trade.selTxnsDetaUrl"), paramMap);
			Map<String, Class> mapClass=new HashMap<String,Class>();
			mapClass.put("rows", Map.class);
			Map<String, Object> map= (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(result),Map.class,mapClass);
			modelAndView.addObject("batchDetail", JSONObject.fromObject(map));
			return modelAndView;
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("服务异常！");
			return modelAndView.addObject("batchDetail", "");
		}finally {
			httpUtils.closeConnection();
		}
	}

	/**
	 * 委托机构交易明细查询
	 * 
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:31:50
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping(value="/selTxnsInfo", produces = "application/json;charset=UTF-8")
	public String selTxnsInfo(String page, String rows, TxnsForPortalBean txnsForPortalBean,
			HttpServletRequest request) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		//String url = "http://192.168.2.130:8100/fe/trade/selTxnsInfo";
		HttpUtils httpUtils = new HttpUtils();
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("page", page);
		paramMap.put("rows", rows);
		paramMap.put("data", JSONObject.fromObject(txnsForPortalBean).toString());
		try {
			httpUtils.openConnection();
			String result = httpUtils.post(configParams.getUrl("trade.selTxnsInfoUrl"), paramMap);
			Map<String, Class> mapClass=new HashMap<String,Class>();
			mapClass.put("rows", Map.class);
			Map<String, Object> map= (Map<String, Object>) JSONObject.toBean(JSONObject.fromObject(result),Map.class,mapClass);
			return JSONObject.fromObject(map).toString();
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("服务异常！");
			Map<String,Object> mapRet = new HashMap<>();
			Map<String,Object> mapErr = new HashMap<>();
			mapErr.put("RET", "err");
			mapErr.put("INFO", "服务异常！");
			mapRet.put("tatal", 0);
			mapRet.put("rows", mapErr);
			return JSONObject.fromObject(mapRet).toString();
		}finally {
			httpUtils.closeConnection();
		}
	}

	/**
	 * 汇总数据pck_sel_txns_info
	 * 
	 * @author: zhangshd
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:32:34
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping(value="/selTxnsStatPortal", produces = "application/json;charset=UTF-8")
	public Map<String, Object> selTxnsStat(TxnsForPortalBean txnsForPortalBean, HttpServletRequest request) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		//String url = "http://192.168.2.130:8100/fe/trade/selTxnsStat";
		HttpUtils httpUtils = new HttpUtils();
		Map<String, String> paramMap = new HashMap<>();
		paramMap.put("data", JSONObject.fromObject(txnsForPortalBean).toString());
		try {
			httpUtils.openConnection();
			String result = httpUtils.post(configParams.getUrl("trade.selTxnsStatUrl"), paramMap);
			return JSONObject.fromObject(result);
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("服务异常！");
			Map<String,Object> mapRet = new HashMap<>();
			Map<String,Object> mapErr = new HashMap<>();
			mapErr.put("RET", "err");
			mapErr.put("INFO", "服务异常！");
			mapRet.put("tatal", 0);
			mapRet.put("rows", mapErr);
			return mapErr;
		}finally {
			httpUtils.closeConnection();
		}
	}
}
