package com.zcbspay.platform.portal.sms.dao;

import com.zcbspay.platform.portal.common.dao.BaseDAO;
import com.zcbspay.platform.portal.sms.enums.ModuleTypeEnum;
import com.zcbspay.platform.portal.sms.pojo.PojoSmsModule;

public interface SmsModuleDAO extends BaseDAO<PojoSmsModule>{

	/**
	 * 获取短信模板信息
	 * @param moduleType
	 * @return
	 */
	public PojoSmsModule getModuleByType(ModuleTypeEnum moduleType);
}
