package com.carbon.system.service.common;

import javax.mail.MessagingException;
import java.io.File;


/**
 * @author Li Jun
 */
public interface MailService {
    /**
     * 发送简单邮件
     * @param toEmail 邮箱地址
     * @param subject 主题
     * @param text 内容
     */
    void simple(String toEmail, String subject, String text);

    /**
     * 发送网页邮件
     * @param toEmail 邮箱地址
     * @param subject 主题
     * @param text 内容
     * @throws MessagingException 异常
     */
    void html(String toEmail, String subject, String text) throws MessagingException;

    /**
     * 发送附件
     * @param toEmail 邮箱地址
     * @param cc cc
     * @param subject 主题
     * @param text 内容
     * @param attachmentFilename 附件名称
     * @param file 附件
     * @throws MessagingException 异常
     */
    void attach(String toEmail, String cc, String subject, String text, String attachmentFilename, File file) throws MessagingException;
}
