package com.zcbspay.platform.portal.query.statistics.service;

import java.io.File;
import java.util.Map;

import com.zcbspay.platform.manager.trade.bean.TxnsForPortalBean;

public interface QueryAndStatService {
	// 交易明细报表 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_txns_deta
	Map<String, Object> queryTxnsDeta(String page, String rows, TxnsForPortalBean txnsForPortalBean);

	Map<String, Object> createTxnsDetaExcelForms(TxnsForPortalBean txnsForPortalBean);

	Map<String, Object> createTxnsDetaTxtForms(TxnsForPortalBean txnsForPortalBean);

	// 交易汇总报表 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_txns_stat
	Map<String, Object> queryTxnsStat(String page, String rows, TxnsForPortalBean txnsForPortalBean);

	Map<String, Object> createTxnsStatExcelForms(TxnsForPortalBean txnsForPortalBean);

	Map<String, Object> createTxnsStatTxtForms(TxnsForPortalBean txnsForPortalBean);

	// 结算单报表 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_setl
	Map<String, Object> queryTxnsSetl(String page, String rows, TxnsForPortalBean txnsForPortalBean);

	Map<String, Object> createTxnsSetlExcelForms(TxnsForPortalBean txnsForPortalBean);

	Map<String, Object> createTxnsSetlTxtForms(TxnsForPortalBean txnsForPortalBean);

	// 对账单 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_bill
	Map<String, Object> queryTxnsBill(String page, String rows, TxnsForPortalBean txnsForPortalBean);

	Map<String, Object> createTxnsBillExcelForms(TxnsForPortalBean txnsForPortalBean);

	Map<String, Object> createTxnsBillTxtForms(TxnsForPortalBean txnsForPortalBean);
	/**
	 * ftp下载
	 * @author: zhangshd
	 * @param fileName
	 * @return boolean
	 * @date: 2017年4月28日 上午10:48:03 
	 * @version v1.0
	 */
	File downForms(String fileName);

}
