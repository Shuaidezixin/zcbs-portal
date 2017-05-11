package com.zcbspay.platform.portal.website.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;
import com.zcbspay.platform.portal.query.statistics.service.QueryAndStatService;
import com.zcbspay.platform.portal.website.util.UserHelper;

/**
 * 交易查询模块
 * @author: zhangshd
 * @date:   2017年5月2日 下午2:35:49   
 * @version :v1.0
 */
@Controller
@RequestMapping("/trade")
@SuppressWarnings("all")
public class TradeQueryController {

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
		//return "trade/queryTrade";
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
//		return "trade/queryBatchTrade";
	}
		
	@RequestMapping("/showQueryTradeDetail")
	public String showQueryTradeDetail() {
		return "trade/queryTradeDetail";
	}
    
    /**
     * 交易查询	批量和实时类交易分别对应两种表头，具体划分为批量代收付和实时代收付四种交易类型pck_sel_txns_single
     * @author: zhangshd
     * @param page
     * @param rows
     * @param txnsForPortalBean
     * @return Map<String,Object>
     * @date: 2017年5月2日 下午2:23:35 
     * @version v1.0
     */
	@ResponseBody
	@RequestMapping("/tradeQuery")
	public Map<String, Object> tradeQuery(String page,String rows,TxnsForPortalBean txnsForPortalBean, HttpServletRequest request) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		return tradeService.selTxnsSingle(page,rows,txnsForPortalBean);
	}
	/**
	 * 批次明细查询  pck_sel_txns_deta
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:23:40 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/tradeQueryForBatch")
	public Map<String, Object> tradeQueryForBatch(@RequestParam(defaultValue = "1") String page,@RequestParam(defaultValue = "10")String rows,TxnsForPortalBean txnsForPortalBean, HttpServletRequest request) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		return tradeService.selTxnsDeta(page,rows,txnsForPortalBean);
	}
	/**
	 * 跳到批次明细查询  pck_sel_txns_deta
	 * @author: 张连海
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return modelAndView
	 * @date: 2017年5月2日 下午2:23:40 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/toTradeQueryForBatch")
	public ModelAndView toTradeQueryForBatch(@RequestParam(defaultValue = "1") String page, @RequestParam(defaultValue = "10") String rows, TxnsForPortalBean txnsForPortalBean, HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView("/trade/queryBatchTradeDetail");
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		Map<String, Object> batchDetail = tradeService.selTxnsDeta(page,rows,txnsForPortalBean);
		modelAndView.addObject("batchDetail", batchDetail);
		modelAndView.addObject("batchno", txnsForPortalBean.getBatchno());
		return modelAndView;
	}
	
	/**
	 * 委托机构交易明细查询
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:31:50 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/selTxnsInfo")
	public Map<String, Object> selTxnsInfo(String page,String rows,TxnsForPortalBean txnsForPortalBean, HttpServletRequest request) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		return tradeService.selTxnsInfo(page,rows,txnsForPortalBean);
	}
	/**
	 * 汇总数据pck_sel_txns_info
	 * @author: zhangshd
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:32:34 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/selTxnsStatPortal")
	public Map<String, Object> selTxnsStat(TxnsForPortalBean txnsForPortalBean, HttpServletRequest request) {
		txnsForPortalBean.setMerid(UserHelper.getCurrentUser(request).getMemberid());
		return tradeService.selTxnsStat(txnsForPortalBean);
	}

}
