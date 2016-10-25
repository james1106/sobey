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
 *  �ʼ����͹�����
 * @author QimouXie
 *
 */
public class SendEmailUtil {
	
	private final transient Properties props = System.getProperties();
	/**
	 * �ʼ���������¼��֤
	 */
	private transient EmailAuthenticator authenticator;

	/**
	 * ����session
	 */
	private transient Session session;
	
	
	public SendEmailUtil(final String smtpHostName, final String username,
			final String password) {
		init(username, password, smtpHostName);
	}
	
	private void init(String username, String password, String smtpHostName) {
		// ��ʼ��props
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.host", smtpHostName);
				// ��֤
				authenticator = new EmailAuthenticator(username, password);
				// ����session
				session = Session.getInstance(props, authenticator);
	}
	
	public void send(String recipient, String subject, Object content)throws AddressException, MessagingException {
		// ����mime�����ʼ�
		final MimeMessage message = new MimeMessage(session);
		// ���÷�����
		message.setFrom(new InternetAddress(authenticator.getUserName()));
		// �����ռ���
		message.setRecipient(RecipientType.TO, new InternetAddress(recipient));
		// ��������
		message.setSubject(subject);
		// �����ʼ�����
		message.setContent(content.toString(), "text/html;charset=utf-8");
		// ����
		Transport.send(message);
	}
	
	

}
