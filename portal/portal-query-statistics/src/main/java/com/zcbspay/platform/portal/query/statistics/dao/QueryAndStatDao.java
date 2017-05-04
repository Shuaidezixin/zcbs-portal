package com.zcbspay.platform.portal.query.statistics.dao;

import java.util.Map;

import com.zcbspay.platform.portal.common.dao.BaseDAO;
import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;

public interface QueryAndStatDao extends BaseDAO<String>{
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
	public Map<String, Object> selTxnsSingleForPortal (String page, String rows,
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
	public Map<String, Object> selTxnsDetaForPortal (String page, String rows,
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
	public Map<String, Object> selTxnsInfoPortal (String page, String rows,
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
	public Map<String, Object> selTxnsStatPortal (String page, String rows,
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
	public Map<String, Object> selOrderPortal (String page, String rows,
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
	public Map<String, Object> selFormsTxnsDetaPortal (String page, String rows,
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
	public Map<String, Object> selFormsTxnsStatPortal (String page, String rows,
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
	public Map<String, Object> selFormsSetlPortal (String page, String rows,
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
	public Map<String, Object> selFormsBillPortal (String page, String rows,
			TxnsForPortalBean txnsForPortalBean);
	
	
}
