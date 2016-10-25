package com.magic.sangha.util;


import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *  邮件发送工具类
 * @author QimouXie
 *
 */
public class SendEmailUtil {
	
	private final transient Properties props = System.getProperties();
	/**
	 * 邮件服务器登录验证
	 */
	private transient EmailAuthenticator authenticator;

	/**
	 * 邮箱session
	 */
	private transient Session session;
	
	
	public SendEmailUtil(final String smtpHostName, final String username,
			final String password) {
		init(username, password, smtpHostName);
	}
	
	private void init(String username, String password, String smtpHostName) {
		// 初始化props
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.host", smtpHostName);
				// 验证
				authenticator = new EmailAuthenticator(username, password);
				// 创建session
				session = Session.getInstance(props, authenticator);
	}
	
	public void send(String recipient, String subject, Object content)throws AddressException, MessagingException {
		// 创建mime类型邮件
		final MimeMessage message = new MimeMessage(session);
		// 设置发信人
		message.setFrom(new InternetAddress(authenticator.getUserName()));
		// 设置收件人
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		// 设置主题
		message.setSubject(subject);
		// 设置邮件内容
		message.setContent(content.toString(), "text/html;charset=utf-8");
		// 发送
		Transport.send(message);
	}
	
	

}
