package com.qheeshow.eway.service.service.impl;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeUtility;

import org.springframework.stereotype.Service;

import com.qheeshow.eway.service.model.MailBean;
import com.qheeshow.eway.service.service.MailService;

@Service
public class MailServiceImpl implements MailService {
	
	/**
	 * 
	 * @Title: sendTextMail
	 * @Description:
	 * @author alan
	 * @param mailInfo
	 * @return
	 * @see com.sns.common.mail.oa.manager.system.service.IMailServer#sendTextMail(com.MailBean.common.mail.oa.manager.system.bean.MailSenderBean)
	 */
	@Override
	public boolean sendTextMail(MailBean mailInfo) {
		Properties pro = mailInfo.getProperties();

		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getInstance(pro, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailInfo.getUserName(), mailInfo.getPassword());
			}
		});
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// 设置邮件消息的主要内容
			String mailContent = mailInfo.getContent();
			mailMessage.setText(mailContent);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @Title: sendHtmlMail
	 * @Description:
	 * @author alan
	 * @param mailInfo
	 * @return
	 * @see com.sns.common.mail.oa.manager.system.service.IMailServer#sendHtmlMail(com.MailBean.common.mail.oa.manager.system.bean.MailSenderBean)
	 */
	@Override
	public boolean sendHtmlMail(MailBean mailInfo) {
		Properties pro = mailInfo.getProperties();

		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session.getInstance(pro, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(mailInfo.getUserName(), mailInfo.getPassword());
			}
		});
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();
			// 创建一个包含HTML内容的MimeBodyPart
			BodyPart html = new MimeBodyPart();
			// 设置HTML内容
			html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
			mainPart.addBodyPart(html);
			
			//添加附件
			for (String file : mailInfo.getAttachFileNames()) {  
                File usFile = new File(file);  
                MimeBodyPart fileBody = new MimeBodyPart();  
                DataSource source = new FileDataSource(file);  
                fileBody.setDataHandler(new DataHandler(source));  
				fileBody.setFileName(usFile.getName());
                try {
					fileBody.setFileName(MimeUtility.encodeText(usFile.getName()));
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                mainPart.addBodyPart(fileBody);  
            }
			
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage);
			return true;
		} catch (MessagingException ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
