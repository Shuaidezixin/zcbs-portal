package com.zcbspay.platform.portal.query.statistics.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zcbspay.platform.portal.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.portal.query.statistics.bean.TxnsForPortalBean;
import com.zcbspay.platform.portal.query.statistics.dao.QueryAndStatDao;

@Repository
public class QueryAndStatDaoImpl extends HibernateBaseDAOImpl<String>  implements QueryAndStatDao{

	@Override
	public Map<String, Object> selTxnsSingle(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		 String[] columns = new String[]{
				 "v_merid", 
				 "v_busicode",
				 "v_stime",
				 "v_etime", 
				 "v_status",
				 "v_orderid",
				 "v_orderid_og",
	             "i_no",
	             "i_perno"
	             };
		 
	        Object[] paramaters = new Object[]{
	        		txnsForPortalBean.getMerid(),
	        		txnsForPortalBean.getBusicode(),
	        		txnsForPortalBean.getStime(),
	        		txnsForPortalBean.getEtime(),txnsForPortalBean.getStatus(),
	        		txnsForPortalBean.getOrderid(),txnsForPortalBean.getOrderidog(),
	                page, rows};
	        return executePageOracleProcedure(
	               "{CALL pck_sel_txns_single.sel_txns_single(?,?,?,?,?,?,?,?,?,?,?)}", columns,
	               paramaters, "cursor0","v_total");
	}

	@Override
	public Map<String, Object> selTxnsDeta(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		 String[] columns = new String[]{
				 "v_batchno", 
				 "v_busicode",
	             "i_no",
	             "i_perno"
	             };
	        Object[] paramaters = new Object[]{
	                txnsForPortalBean.getBatchno(),
	                txnsForPortalBean.getBusicode(),
	                page, rows};
	        return executePageOracleProcedure(
	               "{CALL pck_sel_txns_deta.sel_txns_deta(?,?,?,?,?,?)}", columns,
	               paramaters, "cursor0","v_total");
	}

	@Override
	public Map<String, Object> selTxnsInfo(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		String[] columns = new String[]{
				"v_merid", 
				 "v_busicode",
				 "v_stime",
				 "v_etime", 
				 "v_scommitime",
				 "v_ecommitime",
				 "v_batchno",
				 "v_orderid",
	             "i_no",
	             "i_perno"
	             };
	        Object[] paramaters = new Object[]{
	        		txnsForPortalBean.getMerid(),txnsForPortalBean.getBusicode(),txnsForPortalBean.getStime(),
	        		txnsForPortalBean.getEtime(),txnsForPortalBean.getScommitime(),txnsForPortalBean.getEcommitime(),
	                txnsForPortalBean.getBatchno(),
	                txnsForPortalBean.getOrderid(),
	                page, rows};
	        return executePageOracleProcedure(
	               "{CALL pck_sel_txns_info.sel_txns_info(?,?,?,?,?,?,?,?,?,?,?,?)}", columns,
	               paramaters, "cursor0","v_total");
	}

	@Override
	public  List<?> selTxnsStat(TxnsForPortalBean txnsForPortalBean) {
		String[] columns = new String[]{
				"v_merid", 
				 "v_busicode",
				 "v_stime",
				 "v_etime", 
				 "v_scommitime",
				 "v_ecommitime",
				 "v_batchno",
				 "v_orderid"
	             };
	        Object[] paramaters = new Object[]{
	        		txnsForPortalBean.getMerid(),
	        		txnsForPortalBean.getBusicode(),
	        		txnsForPortalBean.getStime(),
	        		txnsForPortalBean.getEtime(),
	        		txnsForPortalBean.getScommitime(),
	        		txnsForPortalBean.getEcommitime(),
	                txnsForPortalBean.getBatchno(),
	                txnsForPortalBean.getOrderid()};
	       return  executeOracleProcedure("{CALL pck_sel_txns_info.stat_txns_info(?,?,?,?,?,?,?,?,?)}", columns,
	               paramaters, "cursor0");
	        
	}

	@Override
	public Map<String, Object> selOrder(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		String[] columns = new String[]{
				"v_merid", 
				 "v_busicode",
				 "v_stime",
				 "v_etime", 
				 "v_orderid",
				 "v_account",
				 "v_name",
	             "i_no",
	             "i_perno"
	             };
	        Object[] paramaters = new Object[]{
	                txnsForPortalBean.getMerid(),
	                txnsForPortalBean.getBusicode(),
	                txnsForPortalBean.getStime(),
	                txnsForPortalBean.getEtime(),
	                txnsForPortalBean.getOrderid(),
	                txnsForPortalBean.getAccount(),
	                txnsForPortalBean.getName(),
	                page, rows};
	        return executePageOracleProcedure(
	               "{CALL pck_sel_order.sel_order(?,?,?,?,?,?,?,?,?,?,?)}", columns,
	               paramaters, "cursor0","v_total");
	}

	@Override
	public Map<String, Object> selFormsTxnsDeta(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		String[] columns = new String[]{
				"v_merid", 
				 "v_busicode",
				 "v_stime",
				 "v_etime", 
				 "v_scommitime",
				 "v_ecommitime",
				 "v_batchno",
				 "v_orderid",
				 "v_account",
				 "v_name",
	             "i_no",
	             "i_perno"
	             };
	        Object[] paramaters = new Object[]{
	                txnsForPortalBean.getMerid(),
	                txnsForPortalBean.getBusicode(),
	                txnsForPortalBean.getStime(),
	                txnsForPortalBean.getEtime(),
	                txnsForPortalBean.getScommitime(),
	                txnsForPortalBean.getEcommitime(),
	                txnsForPortalBean.getBatchno(),
	                txnsForPortalBean.getOrderid(),
	                txnsForPortalBean.getAccount(),
	                txnsForPortalBean.getName(),
	                page, rows};
	        return executePageOracleProcedure(
	               "{CALL pck_forms_txns_deta.forms_txns_deta(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}", columns,
	               paramaters, "cursor0","v_total");
	}

	@Override
	public Map<String, Object> selFormsTxnsStat(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		String[] columns = new String[]{
				"v_merid", 
				 "v_stime",
				 "v_etime", 
				 "v_stattype",
	             "i_no",
	             "i_perno"
	             };
	        Object[] paramaters = new Object[]{
	                txnsForPortalBean.getMerid(),
	                txnsForPortalBean.getStime(),
	                txnsForPortalBean.getEtime(),
	                txnsForPortalBean.getStattype(),
	                page, rows};
	        return executePageOracleProcedure(
	               "{CALL pck_forms_txns_stat.forms_txns_stat(?,?,?,?,?,?,?,?)}", columns,
	               paramaters, "cursor0","v_total");
	}

	@Override
	public Map<String, Object> selFormsSetl(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		String[] columns = new String[]{
				"v_merid", 
				 "v_stime",
				 "v_etime", 
	             };
	        Object[] paramaters = new Object[]{
	                txnsForPortalBean.getMerid(),
	                txnsForPortalBean.getStime(),
	                txnsForPortalBean.getEtime(),
	                page, rows};
	        return executePageOracleProcedure(
	               "{CALL pck_forms_setl.forms_txns_stat(?,?,?,?,?)}", columns,
	               paramaters, "cursor0","v_total");
	}

	@Override
	public Map<String, Object> selFormsBill(String page, String rows, TxnsForPortalBean txnsForPortalBean) {
		String[] columns = new String[]{
				"v_merid", 
				 "v_stime",
				 "v_etime", 
	             "i_no",
	             "i_perno"
	             };
	        Object[] paramaters = new Object[]{
	                txnsForPortalBean.getMerid(),
	                txnsForPortalBean.getStime(),
	                txnsForPortalBean.getEtime(),
	                page, rows};
	        return executePageOracleProcedure(
	               "{CALL pck_forms_bill.forms_bill(?,?,?,?,?,?,?)}", columns,
	               paramaters, "cursor0","v_total");
	}

	@Override
	public Map<String, Object> selOrderForBatchAndSingle(String page, String rows,
			TxnsForPortalBean txnsForPortalBean) {
		String[] columns = new String[]{
				"v_merid", 
				 "v_busicode",
				 "v_stime",
				 "v_etime", 
				 "v_orderid",
				 "v_account",
				 "v_name",
	             "i_no",
	             "i_perno"
	             };
	        Object[] paramaters = new Object[]{
	                txnsForPortalBean.getMerid(),
	                txnsForPortalBean.getBusicode(),
	                txnsForPortalBean.getStime(),
	                txnsForPortalBean.getEtime(),
	                txnsForPortalBean.getOrderid(),
	                txnsForPortalBean.getAccount(),
	                txnsForPortalBean.getName(),
	                page, rows};
	        return executePageOracleProcedure(
	               "{CALL pck_sel_order.sel_order(?,?,?,?,?,?,?,?,?,?,?)}", columns,
	               paramaters, "cursor0","v_total");
	}
	
}
