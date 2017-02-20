package com.qheeshow.eway.service.model;

/**
 * Created by lihuajun on 17-2-10.
 */
public class Document extends BaseModel {

    private String title;//标题
    private String summary;//摘要
    private String content;//纯文本正文
    private String html;//html正文
    private String indexPic;//首页图片
    private String ishead;//是否头条:0否,1是
    private Integer orderno;//排序
    private Integer status;//状态:0删除,1新稿,2发布

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getIndexPic() {
        return indexPic;
    }

    public void setIndexPic(String indexPic) {
        this.indexPic = indexPic;
    }

    public String getIshead() {
        return ishead;
    }

    public void setIshead(String ishead) {
        this.ishead = ishead;
    }

    public Integer getOrderno() {
        return orderno;
    }

    public void setOrderno(Integer orderno) {
        this.orderno = orderno;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
