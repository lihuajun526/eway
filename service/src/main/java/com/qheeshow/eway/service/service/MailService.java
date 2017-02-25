package com.qheeshow.eway.service.service;

import com.qheeshow.eway.service.model.MailBean;

public interface MailService {

    /**
     * 
    * @Title: sendTextMail 
    * @Description: 发送普通邮件
    * @param mailInfo
    * @return  
    * @return boolean
     */
    public boolean sendTextMail(MailBean mailInfo);
    
    
    /**
     * 
    * @Title: sendHtmlMail 
    * @Description: 发送HTML邮件
    * @param mailInfo
    * @return  
    * @return boolean
     */
    public boolean sendHtmlMail(MailBean mailInfo);
    
}
