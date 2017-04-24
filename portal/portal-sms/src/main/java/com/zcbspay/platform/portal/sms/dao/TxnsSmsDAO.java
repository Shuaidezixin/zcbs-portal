package com.zcbspay.platform.portal.sms.dao;

import com.zcbspay.platform.portal.common.dao.BaseDAO;
import com.zcbspay.platform.portal.sms.enums.ModuleTypeEnum;
import com.zcbspay.platform.portal.sms.pojo.PojoTxnsSms;

public interface TxnsSmsDAO extends BaseDAO<PojoTxnsSms>{

	/**
	 * 获取最新的短信验证码
	 * @param orderNo
	 * @param phoneNo
	 * @return
	 */
	public PojoTxnsSms getLastSMS(String orderNo,String phoneNo);
	
	/**
	 * 保存短信验证码交易信息
	 * @param sms
	 */
	public void saveSMS(PojoTxnsSms sms);
	
	/**
	 * 获取最新的短信验证码
	 * @param moduleType 模板类型
	 * @param phoneNo 手机号
	 * @return
	 */
	public PojoTxnsSms getLastSMS(ModuleTypeEnum moduleType,String phoneNo);
	
	public String getSMSURL();
}
