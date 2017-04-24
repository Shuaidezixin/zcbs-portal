package com.zcbspay.platform.portal.sms.dao.impl;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.portal.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.portal.common.utils.DateUtil;
import com.zcbspay.platform.portal.sms.dao.TxnsSmsDAO;
import com.zcbspay.platform.portal.sms.enums.ModuleTypeEnum;
import com.zcbspay.platform.portal.sms.pojo.PojoTxnsSms;

@Repository("txnsSmsDAO")
public class TxnsSmsDAOImpl extends HibernateBaseDAOImpl<PojoTxnsSms> implements TxnsSmsDAO{
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public PojoTxnsSms getLastSMS(String orderNo,String phoneNo) {
        String hql = " from PojoTxnsSms where tn = ? and overduetime > ? and mobile = ? order by tid desc";
        Query query = getSession().createQuery(hql);
        query.setString(0, orderNo);
        query.setString(1, DateUtil.getCurrentDateTime());
        query.setString(2, phoneNo);
        List<PojoTxnsSms> smsList =  (List<PojoTxnsSms>) query.list();
        if(smsList.size()>0){
            return smsList.get(0);
        }
        return null;
    }
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public PojoTxnsSms getLastSMS(ModuleTypeEnum moduleType,String phoneNo) {
        String hql = " from PojoTxnsSms where moduletype = ? and overduetime > ? and mobile = ? order by tid desc";
        Query query = getSession().createQuery(hql);
        query.setString(0, moduleType.getCode());
        query.setString(1, DateUtil.getCurrentDateTime());
        query.setString(2, phoneNo);
        
		List<PojoTxnsSms> smsList =  (List<PojoTxnsSms>) query.list();
        if(smsList.size()>0){
            return smsList.get(0);
        }
        return null;
    }
	
	

	/**
	 *
	 * @param sms
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=Throwable.class)
	public void saveSMS(PojoTxnsSms sms) {
		 getSession().save(sms);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public String getSMSURL() {
		String hql = "select para_code as PARA_CODE from t_para_dic where para_type = ?";
		SQLQuery query = (SQLQuery) getSession().createSQLQuery(hql).setResultTransformer(
				Transformers.ALIAS_TO_ENTITY_MAP);
		query.setString(0, "SMSURL");
		List<Map<String, Object>> list = query.list();
		if(list.size()>0){
			Map<String, Object> paraDic = list.get(0);
			return paraDic.get("PARA_CODE")+"";
		}
		return null;
	}
}
