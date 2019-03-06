package com.futao.springbootdemo.model.system;

/**
 * 邮件
 *
 * @author futao
 * Created on 2018/10/18.
 */
public class MailM {
    private String[] to;
    private String[] cc;
    private String subject;
    private String content;
    private boolean isHtml;

    public String[] getTo() {
        return to;
    }

    public void setTo(String[] to) {
        this.to = to;
    }

    public String[] getCc() {
        return cc;
    }

    public void setCc(String[] cc) {
        this.cc = cc;
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

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean html) {
        isHtml = html;
    }
}
