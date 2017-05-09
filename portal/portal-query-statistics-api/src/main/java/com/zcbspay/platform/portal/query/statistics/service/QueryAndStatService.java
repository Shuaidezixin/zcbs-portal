package com.zcbspay.platform.portal.query.statistics.service;

import java.io.File;
import java.util.List;
import java.util.Map;

import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;

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
	File downForms(String fileName,String packageName);
	
	/**
	 * 交易查询-实时类
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param batchPaymentOrderBean
	 * @return Map<String,Object>
	 * @date: 2017年4月25日 下午2:13:17 
	 * @version v1.0
	 */
	public Map<String, Object> selTxnsSingle (String page, String rows,
			TxnsForPortalBean txnsForPortalBean);
	/**
	 * 交易查询--批量明细
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param batchPaymentOrderBean
	 * @return Map<String,Object>
	 * @date: 2017年4月25日 下午2:13:50 
	 * @version v1.0
	 */
	public Map<String, Object> selTxnsDeta (String page, String rows,
			TxnsForPortalBean txnsForPortalBean);
	
	/**
	 * 交易明细
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param batchPaymentOrderBean
	 * @return Map<String,Object>
	 * @date: 2017年4月25日 下午2:13:50 
	 * @version v1.0
	 */
	public Map<String, Object> selTxnsInfo (String page, String rows,
			TxnsForPortalBean txnsForPortalBean);
	/**
	 * 交易明细汇总
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param batchPaymentOrderBean
	 * @return Map<String,Object>
	 * @date: 2017年4月25日 下午2:13:50 
	 * @version v1.0
	 */
	public List<?> selTxnsStat (
			TxnsForPortalBean txnsForPortalBean);
	
	
	/**
	 * 交易明细汇总
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param batchPaymentOrderBean
	 * @return Map<String,Object>
	 * @date: 2017年4月25日 下午2:13:50 
	 * @version v1.0
	 */
	public Map<String, Object> selOrder (String page, String rows,
			TxnsForPortalBean txnsForPortalBean);
	
	/**
	 * 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_txns_deta
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年4月26日 下午3:41:15 
	 * @version v1.0
	 */
	public Map<String, Object> selFormsTxnsDeta (String page, String rows,
			TxnsForPortalBean txnsForPortalBean);
	
	/**
	 * 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_txns_stat
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年4月26日 下午3:41:15 
	 * @version v1.0
	 */
	public Map<String, Object> selFormsTxnsStat (String page, String rows,
			TxnsForPortalBean txnsForPortalBean);
	
	/**
	 * 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_setl
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年4月26日 下午3:41:15 
	 * @version v1.0
	 */
	public Map<String, Object> selFormsSetl (String page, String rows,
			TxnsForPortalBean txnsForPortalBean);
	
	
	/**
	 * 报表预查询功能，报表下载功能，生成的报表文件放入ftp服务器中并从ftp服务器中下载pck_forms_bill
	 * @author: zhangshd
	 * @param page
	 * @param rows
	 * @param txnsForPortalBean
	 * @return Map<String,Object>
	 * @date: 2017年4月26日 下午3:41:15 
	 * @version v1.0
	 */
	public Map<String, Object> selFormsBill (String page, String rows,
			TxnsForPortalBean txnsForPortalBean);
	/**
	 * 获取ftp特定文件夹下的文件列表
	 * @author: zhangshd
	 * @param fileName
	 * @param packageName
	 * @return Map<String,Object>
	 * @date: 2017年5月5日 下午5:48:47 
	 * @version v1.0
	 */
	Map<String, Object> getFileInfo(String packageName);

	Map<String, Object> orderForBatchAndSingle(String page, String rows, TxnsForPortalBean txnsForPortalBean);

}
