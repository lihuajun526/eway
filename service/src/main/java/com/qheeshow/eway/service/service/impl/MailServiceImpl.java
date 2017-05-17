package com.qheeshow.eway.service.service.impl;

import com.qheeshow.eway.service.model.MailBean;
import com.qheeshow.eway.service.service.MailService;
import com.sun.mail.util.MailSSLSocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

@Service
public class MailServiceImpl implements MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    /**
     * @param mailInfo
     * @return
     */
    @Override
    public boolean sendTextMail(MailBean mailInfo) {

        try {
            Properties pro = this.getProperties(mailInfo);
            // 根据邮件会话属性和密码验证器构造一个发送邮件的session
            Session sendMailSession = Session.getInstance(pro, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailInfo.getUserName(), mailInfo.getPassword());
                }
            });
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
        } catch (Exception e) {
            LOGGER.error("error:", e);
        }
        return false;
    }

    /**
     * @param mailInfo
     * @return
     */
    @Override
    public boolean sendHtmlMail(MailBean mailInfo) {

        try {
            Properties pro = this.getProperties(mailInfo);
            // 根据邮件会话属性和密码验证器构造一个发送邮件的session
            Session sendMailSession = Session.getInstance(pro, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(mailInfo.getUserName(), mailInfo.getPassword());
                }
            });
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
            if (mailInfo.getAttachFileNames() != null && mailInfo.getAttachFileNames().length > 0) {
                for (String file : mailInfo.getAttachFileNames()) {
                    File usFile = new File(file);
                    MimeBodyPart fileBody = new MimeBodyPart();
                    DataSource source = new FileDataSource(file);
                    fileBody.setDataHandler(new DataHandler(source));
                    fileBody.setFileName(usFile.getName());
                    try {
                        fileBody.setFileName(MimeUtility.encodeText(usFile.getName()));
                    } catch (UnsupportedEncodingException e) {
                        LOGGER.error("添加附件[{}]失败", usFile.getName(), e);
                    }
                    mainPart.addBodyPart(fileBody);
                }
            }
            // 将MiniMultipart对象设置为邮件内容
            mailMessage.setContent(mainPart);
            // 发送邮件
            Transport.send(mailMessage);
            return true;
        } catch (Exception e) {
            LOGGER.error("error:", e);
        }
        return false;
    }

    private Properties getProperties(MailBean mailBean) throws GeneralSecurityException {
        Properties p = new Properties();
        p.put("mail.smtp.host", mailBean.getMailServerHost());
        p.put("mail.smtp.port", mailBean.getMailServerPort());
        p.put("mail.smtp.auth", mailBean.isValidate() ? "true" : "false");
        //开启安全协议
        MailSSLSocketFactory sf = null;
        sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        p.put("mail.smtp.ssl.enable", "true");
        p.put("mail.smtp.ssl.socketFactory", sf);
        return p;
    }

}
