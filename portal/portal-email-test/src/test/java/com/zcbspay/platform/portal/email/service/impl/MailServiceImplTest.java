package com.zcbspay.platform.portal.email.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zcbspay.platform.portal.email.bean.MailBean;
import com.zcbspay.platform.portal.email.service.MailService;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration("/spring-context.xml")
public class MailServiceImplTest {
	@Autowired
	private MailService mailService;
	@Test
	public void testMailSend() {
		MailBean mailBean = new MailBean();
		mailBean.setTo("2508456678@qq.com");
		mailBean.setSubject("测试邮件");
		mailBean.setContent("测试邮件的内容");
		try {
			mailService.mailSend(mailBean);
			Thread.sleep(3000);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
