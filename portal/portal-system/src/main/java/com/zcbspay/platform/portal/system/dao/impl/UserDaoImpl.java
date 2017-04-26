package com.zcbspay.platform.portal.system.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.zcbspay.platform.portal.common.dao.impl.HibernateBaseDAOImpl;
import com.zcbspay.platform.portal.common.utils.MD5Util;
import com.zcbspay.platform.portal.system.api.bean.UserBean;
import com.zcbspay.platform.portal.system.dao.UserDao;
@Repository
public class UserDaoImpl extends HibernateBaseDAOImpl<String>  implements UserDao{

	@Override
	public Map<String, Object> queryUsers(UserBean userbean, String page, String rows) {
		String[] columns = new String[] { "v_user_name","v_login_name", "v_member_id", "i_no", "i_perno" };

		Object[] paramaters = new Object[] { userbean.getUserName(),userbean.getLoginName(),userbean.getMemberid(), page, rows };
		return executePageOracleProcedure("{CALL pck_t_user_info.sel_t_user_info(?,?,?,?,?,?,?)}", columns, paramaters,
				"cursor0", "v_total");
	}

	@Override
	public String saveUser(UserBean userBean) {
		Object[] paramaters = new Object[] { userBean.getUserName(), userBean.getMemberid(), userBean.getLoginName(),MD5Util.MD5(userBean.getPwd()),userBean.getPhone(),userBean.getEmail(),userBean.getUpdateUser(),
				userBean.getNotes()};
		String[] columns = new String[] { "v_user_name", "v_member_id", "v_login_name", "v_user_password", "v_user_phone","v_user_email","v_create_user","v_notes" };
		Object total = executeOracleProcedure("{CALL pck_t_user_info.ins_t_user_info(?,?,?,?,?,?,?,?,?)}", columns, paramaters,
				"cursor0").get(0).get("INFO");
		return (String) total;
	}

	@Override
	public String updateUser(UserBean userBean) {
		Object[] paramaters = new Object[] {userBean.getUserId(), userBean.getUserName(), userBean.getMemberid(), 
				userBean.getLoginName(),MD5Util.MD5(userBean.getPwd()),userBean.getPhone(),
				userBean.getEmail(),userBean.getErrorTime(),userBean.getCreator(),
				userBean.getStatus(),userBean.getNotes()};
		String[] columns = new String[] {"v_userid", "v_user_name", "v_member_id", 
										"v_login_name", "v_user_password", "v_user_phone",
										"v_user_email","v_error_times","v_uptate_user",
										"v_status","v_notes" };
		Object total = executeOracleProcedure("{CALL pck_t_user_info.upt_t_user_info(?,?,?,?,?,?,?,?,?,?,?,?)}", columns, paramaters,
				"cursor0").get(0).get("INFO");
		return (String) total;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String login(UserBean userbean) {
		String queryString ="select user_login(?,?,?) from dual";
		String[] paramaters={userbean.getMemberid(),userbean.getLoginName(),MD5Util.MD5((userbean.getPwd()))};
		List<?> result =queryBySQL(queryString, paramaters);
		return (String)((Map<String, Object>) result.get(0)).get("USER_LOGIN(:1,:2,:3)");
	}
	
}
