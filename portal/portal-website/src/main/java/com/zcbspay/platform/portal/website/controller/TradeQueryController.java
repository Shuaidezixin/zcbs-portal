package com.zcbspay.platform.portal.website.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;
import com.zcbspay.platform.portal.query.statistics.service.QueryAndStatService;
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
   
    
	@RequestMapping("showQueryTrade")
	public String showBusiRate() {
		return "trade/queryTrade";
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
	public Map<String, Object> tradeQuery(String page,String rows,TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selTxnsSingleForPortal(page,rows,txnsForPortalBean);
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
	public Map<String, Object> tradeQueryForBatch(String page,String rows,TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selTxnsSingleForPortal(page,rows,txnsForPortalBean);
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
	public Map<String, Object> selTxnsInfo(String page,String rows,TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selTxnsInfoPortal(page,rows,txnsForPortalBean);
	}
	/**
	 * 汇总数据pck_sel_txns_info
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年5月2日 下午2:32:34 
	 * @version v1.0
	 */
	@ResponseBody
	@RequestMapping("/selTxnsStatPortal")
	public Map<String, Object> selTxnsStat(String page,String rows,TxnsForPortalBean txnsForPortalBean) {
		return tradeService.selTxnsStatPortal(page,rows,txnsForPortalBean);
	}

}
