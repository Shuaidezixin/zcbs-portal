package com.zcbspay.platform.portal.sms.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.portal.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.portal.sms.dao.SmsModuleDAO;
import com.zcbspay.platform.portal.sms.enums.ModuleTypeEnum;
import com.zcbspay.platform.portal.sms.pojo.PojoSmsModule;

@Repository("smsModuleDAO")
public class SmsModuleDAOImpl extends HibernateBaseDAOImpl<PojoSmsModule> implements SmsModuleDAO{

	/**
	 *
	 * @param moduleType
	 * @return
	 */
	@Override
	@Transactional(readOnly = true)
	public PojoSmsModule getModuleByType(ModuleTypeEnum moduleType) {
		String hql = "from PojoSmsModule where moduletype = ?";
		Query query = getSession().createQuery(hql);
		query.setString(0, moduleType.getCode());
		return (PojoSmsModule) query.uniqueResult();
	}

	
}
