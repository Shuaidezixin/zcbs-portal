package com.zcbspay.platform.portal.query.enums;

public enum FormTypeEnum {

	// 结算单报表
	deta("deta", "交易明细报表"),
	// 交易明细报表
	stat("stat", "交易汇总报表"),
	// 结算单报表
	setl("setl", "结算单报表"),
	// 对账单报表
	bill("bill", "结算单报表")
	;

	private String packageName; // 交易类型
	private String type; // 交易子类型

	public String getPackageName() {
		return packageName;
	}

	public String getType() {
		return type;
	}

	private FormTypeEnum(String packageName, String type) {
		this.packageName = packageName;
		this.type = type;
	}

	 // 普通方法  
    public static FormTypeEnum getFormTypeEnum(String packageName) {  
        for (FormTypeEnum txnTypeEnum : FormTypeEnum.values()) {  
            if (txnTypeEnum.getPackageName().equals(packageName)) {  
                return txnTypeEnum;  
            }  
        }  
        return null;  
    }  
	
	
}
