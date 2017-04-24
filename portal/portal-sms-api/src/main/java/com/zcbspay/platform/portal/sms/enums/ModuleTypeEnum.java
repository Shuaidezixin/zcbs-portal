package com.zcbspay.platform.portal.sms.enums;

public enum ModuleTypeEnum {
	/**1001:注册;;;;;**/
	REGISTER("1001"),
    /**1002:支付**/
    PAY("1002"),
    /**1003:修改登录密码**/
    CHANGELOGINPWD("1003"),
    /**1004:修改支付密码**/
    CHANGEPAYPWD("1004"),
    /**1005:绑卡**/
    BINDCARD("1005"),
    /**账户支付**/
    ACCOUNTPAY("1006"),
    /**账户支付**/
    
    /**匿名支付**/
    ANONYMOUSPAY("1008"),
    /**重置支付密码*/
    RESETPAYPWD("1009"),
    /**秘钥上传**/
    SECRETKEY("1010"),
    /**企业实名认证*/
    ENTERPRISEREALNAME("1011"),
    /**解绑手机**/
    UNBINDPHONE("1012"),

    /**绑定手机号*/
    BINDPHONE("1007"),
    /**未知状态**/
    UNKNOW("99");

 private String code;
    
    private ModuleTypeEnum(String code){
        this.code = code;
    }
    
    public static ModuleTypeEnum fromValue(String value) {
        for(ModuleTypeEnum status:values()){
            if(status.code.equals(value)){
                return status;
            }
        }
        return UNKNOW;
    }
    
    public String getCode(){
        return code;
    }
}
