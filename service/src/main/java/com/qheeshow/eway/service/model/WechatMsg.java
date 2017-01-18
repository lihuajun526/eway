package com.qheeshow.eway.service.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Created by lihuajun on 2016/8/17.
 */
public class WechatMsg {

    private static final Logger LOGGER = LoggerFactory.getLogger(WechatMsg.class);

    private String toUserName;
    private String fromUserName;
    private Date createTime;
    private String msgType;
    private String event;
    private String eventKey;
    private String ticket;
    private String content;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getTicket() {
        return ticket;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String toXml() {
        StringBuffer sb = new StringBuffer("<xml>");
        Field[] fields = this.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                String key = field.getName();
                if (key.equalsIgnoreCase("LOGGER"))
                    continue;
                String typeName = field.getType().getSimpleName();
                byte[] b = {(byte) (key.charAt(0) - 32)};
                key = new String(b) + key.substring(1);
                Method method = this.getClass().getMethod("get" + key);
                if (typeName.equalsIgnoreCase("String")) {
                    String value = (String) method.invoke(this);
                    if (StringUtils.isEmpty(value))
                        continue;
                    sb.append("<").append(key).append(">").append("<![CDATA[").append(value).append("]]></").append(key).append(">");
                } else if (typeName.equalsIgnoreCase("Date")) {
                    Date value = (Date) method.invoke(this);
                    if (StringUtils.isEmpty(value))
                        continue;
                    sb.append("<").append(key).append(">").append(value.getTime()).append("</").append(key).append(">");
                } else {

                }

            }
        } catch (Exception e) {
            LOGGER.error("将微信消息转为xml格式时出错:", e);
        }
        sb.append("</xml>");
        return sb.toString();
    }
}
