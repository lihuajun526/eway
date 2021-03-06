package com.qheeshow.eway.service.model;


public class MailBean implements java.io.Serializable {

    private static final long serialVersionUID = 1L;

    private String mailServerHost = "smtp.exmail.qq.com";     //发送邮件的服务器的IP(或主机地址)

    private String mailServerPort = "465"; //发送邮件的服务器的端口

    private String fromAddress = "service@qheefund.com";  //发件人邮箱地址

    private String toAddress;  //收件人邮箱地址

    private String userName="service@qheefund.com"; //登陆邮件发送服务器的用户名

    private String password="wutongE123456"; //登陆邮件发送服务器的密码

    private boolean validate = true; //是否需要身份验证

    private String subject; //邮件主题

    private String content; //邮件的文本内容

    private String[] attachFileNames; //邮件附件的文件名

    public String getMailServerHost() {
        return mailServerHost;
    }

    public String getMailServerPort() {
        return mailServerPort;
    }

    public boolean isValidate() {
        return validate;
    }

    public void setValidate(boolean validate) {
        this.validate = validate;
    }

    public String[] getAttachFileNames() {
        return attachFileNames;
    }

    public void setAttachFileNames(String[] fileNames) {
        this.attachFileNames = fileNames;
    }

    public String getFromAddress() {
        return fromAddress;
    }

    public String getPassword() {
        return password;
    }

    public String getToAddress() {
        return toAddress;
    }

    public void setToAddress(String toAddress) {
        this.toAddress = toAddress;
    }

    public String getUserName() {
        return userName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String textContent) {
        this.content = textContent;
    }
}
