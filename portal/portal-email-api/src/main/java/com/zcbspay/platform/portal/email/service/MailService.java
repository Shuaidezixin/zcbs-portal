package com.zcbspay.platform.portal.email.service;

import java.util.Map;

import com.zcbspay.platform.portal.email.bean.MailBean;
import com.zcbspay.platform.portal.email.bean.ResultBean;

/**
 * 邮件发送接口
 * 
 * @date 2017年4月21日 下午3:30:04
 * @author 张连海
 * @Description:
 */
public interface MailService {
	
	/**
	 * 根据模板名称查找模板，加载模板内容后发送邮件
	 * 
	 * @Date:2017年4月21日 上午11:02:58
	 * @author 张连海
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param map
	 *            邮件内容与模板内容转换对象
	 * @param templateName
	 *            模板文件名称
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	ResultBean sendMailByTemplate(String receiver, String subject,
			Map<String, String> map, String templateName);

	/**
	 * 根据模板名称查找模板，加载模板内容后发送邮件,并且附带文件附件
	 * 
	 * @Date:2017年4月21日 上午11:02:58
	 * @author 张连海
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param map
	 *            邮件内容与模板内容转换对象
	 * @param templateName
	 *            模板文件名称
	 * @throws IOException
	 * @throws TemplateException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	ResultBean sendMailAndFileByTemplate(String receiver,
			String subject, String filePath, Map<String, String> map,
			String templateName);

	/**
	 * 普通方式发送邮件内容
	 * 
	 * @Date:2017年4月21日 下午1:20:47
	 * @author 张连海
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param maiBody
	 *            邮件正文
	 * @throws IOException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	ResultBean sendMail(String receiver, String subject, String maiBody);

	/**
	 * 普通方式发送邮件内容，并且附带文件附件
	 * 
	 * @Date:2017年4月21日 下午1:20:47
	 * @author 张连海
	 * @param receiver
	 *            收件人地址
	 * @param subject
	 *            邮件主题
	 * @param filePath
	 *            文件的绝对路径
	 * @param maiBody
	 *            邮件正文
	 * 
	 * @throws IOException
	 * @throws MessagingException
	 * @Description:
	 * @return void
	 */
	ResultBean sendMailAndFile(String receiver, String subject,
			String filePath, String maiBody);
}
