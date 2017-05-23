package com.zcbspay.platform.portal.email.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.zcbspay.platform.portal.email.bean.ResultBean;
import com.zcbspay.platform.portal.email.service.MailService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-context.xml")
public class MailServiceImplTest {
	@Autowired
	private MailService mailService;

	@Test
	public void testMailTemplate() {
		String templateName = "template_1.ftl";
		Map<String, String> map = new HashMap<String, String>();
		map.put("content", "test");
		ResultBean result = mailService.sendMailByTemplate("949435735@qq.com", "test", map, templateName);
		System.out.println(result.toString());
	}

	@Test
	public void testMail() {
		/*ResultBean result = mailService.sendMail("949435735@qq.com", "test", "普通邮件");
		System.out.println(result.toString());*/
	}

	@Test
	public void testMailAndFile() {
		/*String filePath = "D:/dat.txt";
		ResultBean result = mailService.sendMailAndFile("949435735@qq.com", "test", filePath, "ted");
		System.out.println(result.toString());*/
	}

}
