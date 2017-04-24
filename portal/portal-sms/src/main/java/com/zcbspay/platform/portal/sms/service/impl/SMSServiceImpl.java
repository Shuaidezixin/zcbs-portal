package com.zcbspay.platform.portal.sms.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.portal.common.utils.DateUtil;
import com.zcbspay.platform.portal.sms.dao.SmsModuleDAO;
import com.zcbspay.platform.portal.sms.dao.TxnsSmsDAO;
import com.zcbspay.platform.portal.sms.enums.ModuleTypeEnum;
import com.zcbspay.platform.portal.sms.pojo.PojoSmsModule;
import com.zcbspay.platform.portal.sms.pojo.PojoTxnsSms;
import com.zcbspay.platform.portal.sms.service.SMSService;
import com.zcbspay.platform.portal.sms.utils.HttpRequestParam;
import com.zcbspay.platform.portal.sms.utils.HttpUtils;

@Service("smsService")
@Transactional
public class SMSServiceImpl implements SMSService{

	private static final Log log = LogFactory.getLog(SMSServiceImpl.class);
	private final static ResourceBundle RESOURCE = ResourceBundle.getBundle("sms_config");
	private static final String ENCODE="UTF-8";
	@Autowired
	private SmsModuleDAO smsModuleDAO;
	@Autowired
	private TxnsSmsDAO txnsSmsDAO;
	
	
	/**
	 *
	 * @param moduleType
	 * @param phoneNo
	 * @param orderNo
	 * @param args
	 * @return
	 */
	@Override
	public int sendSMS(ModuleTypeEnum moduleType, String phoneNo,
			String orderNo, String... args) {
		String code = getVerifyCode();
		String[] smsPara = null;
		PojoSmsModule smsModule = smsModuleDAO.getModuleByType(moduleType);
		if(args!=null){
			smsPara = new String[args.length+1];
			smsPara[0]=code;
			for(int i=0;i<args.length;i++){
				smsPara[i+1]=args[i];
			}
		}
		String content = null;
		try {
			content = String.format(smsModule.getModulecont(), smsPara);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("短信参数错误");
		}
		int inputLine = 999;
		try {
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("Name", RESOURCE.getString("sms_username"));
			paramMap.put("Passwd", RESOURCE.getString("sms_pwd"));
			paramMap.put("Phone", phoneNo);
			paramMap.put("Content", content);
			HttpUtils httpUtils = new HttpUtils();
			httpUtils.openConnection();
			
			String sms_url=txnsSmsDAO.getSMSURL();
			log.info("start send sms,the mobile phone numbuer:"+phoneNo+"---send url:"+sms_url+" send content: "+content);
			String responseContent = httpUtils.executeHttpPost(sms_url, setHttpParams(paramMap), ENCODE);
			httpUtils.closeConnection();
			inputLine = Integer.valueOf(responseContent);
			PojoTxnsSms sms = new PojoTxnsSms();
			sms.setVerifycode(code);
			sms.setTn(orderNo);
			sms.setMobile(phoneNo);
			sms.setSendtime(DateUtil.getCurrentDateTime());
			sms.setOverduetime(DateUtil.formatDateTime(DateUtil.addMin(new Date(), smsModule.getValiytime()/60)));
			sms.setRetcode(responseContent);
			sms.setModuletype(moduleType.getCode());
			sms.setContent(content);
			switch (inputLine) {
			    case 100 :
			        sms.setRetinfo("短信发送成功");
			        break;
			    case 101 :
			        sms.setRetinfo("账号密码不能为空");
			        break;
			    case 102 :
			        sms.setRetinfo("手机号,短信内容均不能为空");
			        break;
			    case 103 :
			        sms.setRetinfo("数据库连接失败");
			        break;
			    case 104 :
			        sms.setRetinfo("账号密码错误");
			        break;
			    case 105 :
			        sms.setRetinfo("短信发送成功,等待审核!");
			        break;
			    case 106 :
			        sms.setRetinfo("短信发送失败");
			        break;
			    case 999 :
			        sms.setRetinfo("未知错误");
			        break;
			}
			txnsSmsDAO.saveSMS(sms);
			log.info("end send sms, response content is :" + responseContent);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (HttpException e) {
			e.printStackTrace();
		}
		return inputLine;
	}

	/**
	 *
	 * @param phoneNo
	 * @param orderNo
	 * @param code
	 * @return
	 */
	@Override
	public int verifyCode(String phoneNo, String orderNo, String code) {
		PojoTxnsSms sms = txnsSmsDAO.getLastSMS(orderNo, phoneNo);
		if(sms==null){
            //resultBean = new ResultBean("0051", "交易失败，验证码已过期");
			return 3;
        }else{
            if(!sms.getVerifycode().equals(code)){
                return 2;
            }
        }
		return 1;
	}
	
	/**
	 * 生成6位短信验证码
	 * @return
	 */
	private String getVerifyCode(){
       String verifyCode = "";
       for(int i=0;i<6;i++){
           int x=1+(int)(Math.random()*9);
           verifyCode+=x;
       }
       return verifyCode;
   }
	/**
     * 设置请求参数
     * @param
     * @return
     */
    private List<HttpRequestParam> setHttpParams(Map<String, String> paramMap) {
        List<HttpRequestParam> formparams = new ArrayList<HttpRequestParam>();
        Set<Map.Entry<String, String>> set = paramMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            formparams.add(new HttpRequestParam(entry.getKey(), entry.getValue()));
        }
        return formparams;
    }

	/**
	 *
	 * @param moduleType
	 * @param phoneNo
	 * @param code
	 * @return
	 */
	@Override
	public int verifyCode(ModuleTypeEnum moduleType, String phoneNo, String code) {
		PojoTxnsSms sms = txnsSmsDAO.getLastSMS(moduleType, phoneNo);
		if(sms==null){
			return 3;
        }else{
            if(!sms.getVerifycode().equals(code)){
                return 2;
            }
        }
		return 1;
	}
	
	
	public String generateCode(ModuleTypeEnum moduleType,String phoneNo,String orderNo){
		String code = getVerifyCode();
		PojoSmsModule smsModule = smsModuleDAO.getModuleByType(moduleType);
		
		PojoTxnsSms sms = new PojoTxnsSms();
		sms.setVerifycode(code);
		sms.setTn(orderNo);
		sms.setMobile(phoneNo);
		sms.setSendtime(DateUtil.getCurrentDateTime());
		sms.setOverduetime(DateUtil.formatDateTime(DateUtil.addMin(new Date(), smsModule.getValiytime()/60)));
		//sms.setRetcode(responseContent);
		sms.setModuletype(moduleType.getCode());
		//sms.setContent(content);
		txnsSmsDAO.saveSMS(sms);
		return code;
	}
}
