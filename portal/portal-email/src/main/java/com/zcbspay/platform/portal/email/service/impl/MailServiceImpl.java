package com.zcbspay.platform.portal.email.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.zcbspay.platform.portal.email.bean.MailBean;
import com.zcbspay.platform.portal.email.service.MailService;

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
	
	@Autowired
	private JavaMailSender mailSender;// spring配置中定义
	@Autowired
	private SimpleMailMessage simpleMailMessage;// spring配置中定义
	@Autowired
	private ThreadPoolTaskExecutor threadPool;

	/**
	 * 发送模板邮件
	 * 
	 * @param 需要设置四个参数
	 *            templateName,toMail,subject,mapModel
	 * @throws Exception
	 * 
	 */
	@Override
	public void mailSend(final MailBean mailBean) {
		threadPool.execute(new Runnable() {
			public void run() {
				try {
//					System.out.println("----------邮件信息："+ mailBean.toString() +"----------");
					simpleMailMessage.setFrom(simpleMailMessage.getFrom()); // 发送人,从配置文件中取得
					simpleMailMessage.setTo(mailBean.getTo()); // 接收人
					simpleMailMessage.setSubject(mailBean.getSubject());
					simpleMailMessage.setText(mailBean.getContent());
//					System.out.println("----------开始 邮件 发送----------");
					mailSender.send(simpleMailMessage);
//					System.out.println("----------邮件 已经 发送----------");
				} catch (MailException e) {
					e.printStackTrace();
					logger.error("邮件发送服务异常!");
				}
			}
		});
	}

}
