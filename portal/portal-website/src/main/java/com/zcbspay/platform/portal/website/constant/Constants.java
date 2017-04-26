package com.zcbspay.platform.portal.website.constant;

public class Constants {

    private Constants() {
    }


    public static class LoginCanstant {
        public static final String LOGIN_USER = "LOGIN_USER";
        
        public static final String LOGIN_USER_NAME="login_user_name";

        private LoginCanstant() {
        }
    }
    
    
    public static class ReturnCanstant {
        public static final String SUCCESS = "success";
        
        public static final String ERROT="error";

        private ReturnCanstant() {
        }
    }
    
    
    
    /**
     * 时间相关常量
     */
    public static class DateCanstant {
        public static final String EXCEL_DATE = "yyyyMMdd";
        public static final String EXCEL_MONTH = "yyyyMM";
        public static final String DATE_MONTH="yyyy-MM";
        public static final String MMDD = "MM-dd";
        public static final String DATE = "yyyy-MM-dd";
        public static final String CHINESE_DATE = "yyyy年MM月dd日";
        public static final String CHINESE_MMDD = "MM月dd日";
        public static final String DATE_TIME12 = "yyyy-MM-dd hh:mm:ss";
        public static final String DATE_TIME24 = "yyyy-MM-dd HH:mm:ss";

        private DateCanstant() {
        }
    }
    
    /**
	 * 发送、接收标志
		SR00：发送sendReceiveType;
		SR01：接收
	 */
    public static class SendReceiveType{
        public static final String SEND = "SR00";
        public static final String RECEIVE = "SR01";
        private SendReceiveType() {
        }
    }
	
    /**
     * web相关常量
     */
    public static class Web {

        public static final String COOKIE_ROOT_PATH = "/";
        /**
         * 路径分隔符
         */
        public static final String SPT = "/";
        /**
         * 索引页
         */
        public static final String INDEX = "index";
        /**
         * UTF-8编码
         */
        public static final String UTF8 = "UTF-8";
        /**
         * 提示信息
         */
        public static final String MESSAGE = "message";
        /**
         * cookie中的JSESSIONID名称
         */
        public static final String JSESSION_COOKIE = "JSESSIONID";
        /**
         * url中的jsessionid名称
         */
        public static final String JSESSION_URL = "jsessionid";
        /**
         * HTTP POST请求
         */
        public static final String POST = "POST";
        /**
         * HTTP GET请求
         */
        public static final String GET = "GET";
    }

    
    
    
}
