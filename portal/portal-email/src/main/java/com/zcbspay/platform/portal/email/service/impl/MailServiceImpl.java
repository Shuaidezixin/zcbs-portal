package com.zcbspay.platform.portal.email.service.impl;

import java.io.IOException;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.portal.email.bean.ResultBean;
import com.zcbspay.platform.portal.email.service.MailService;
import com.zcbspay.platform.portal.email.service.utils.MailUtil;

import freemarker.template.TemplateException;

/**
 * 邮件发送业务逻辑类
 * 
 * @author lianhai
 *
 */
@Service("mailService")
@Transactional
public class MailServiceImpl implements MailService {
	private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);
	
	@Override
	public ResultBean sendMailByTemplate(String receiver, String subject, Map<String, String> map,
			String templateName) {
		try {
			MailUtil.sendMailByTemplate(receiver, subject, map, templateName);
			return new ResultBean("邮件发送成功！");
		} catch (TemplateException e) {
			e.printStackTrace();
			logger.info("无法解析模板文件！");
			return new ResultBean("", "无法解析模板文件！");
		} catch (IOException | MessagingException e) {
			e.printStackTrace();
			logger.error("邮件发送失败！");
			return new ResultBean("", "邮件发送失败！");
		}
	}

	@Override
	public ResultBean sendMailAndFileByTemplate(String receiver, String subject, String filePath,
			Map<String, String> map, String templateName) {
		try {
			MailUtil.sendMailAndFileByTemplate(receiver, subject, filePath, map, templateName);
			return new ResultBean("邮件发送成功！");
		} catch (TemplateException e) {
			logger.info("无法解析模板文件！");
			return new ResultBean("", "无法解析模板文件！");
		} catch (IOException | MessagingException e) {
			logger.error("邮件发送失败！");
			return new ResultBean("", "邮件发送失败！");
		}
	}

	@Override
	public ResultBean sendMail(String receiver, String subject, String maiBody) {
		try {
			MailUtil.sendMail(receiver, subject, maiBody);
			return new ResultBean("邮件发送成功！");
		} catch (IOException | MessagingException e) {
			logger.error("邮件发送失败！");
			return new ResultBean("", "邮件发送失败！");
		}
	}

	@Override
	public ResultBean sendMailAndFile(String receiver, String subject, String filePath, String maiBody) {
		try {
			MailUtil.sendMailAndFile(receiver, subject, filePath, maiBody);
			return new ResultBean("邮件发送成功！");
		}  catch (IOException | MessagingException e) {
			logger.error("邮件发送失败！");
			return new ResultBean("", "邮件发送失败！");
		}
	}

}
