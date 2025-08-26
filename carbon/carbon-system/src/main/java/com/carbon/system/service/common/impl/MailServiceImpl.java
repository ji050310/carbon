package com.carbon.system.service.common.impl;

import cn.hutool.core.util.StrUtil;
import com.carbon.system.service.common.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.File;
import java.io.UnsupportedEncodingException;

/**
 * @author Li Jun
 */
@Slf4j
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;


    @Override
    public void simple(String toEmail, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        // 发件人邮箱
        message.setFrom("1603963073@qq.com");
        // 收信人邮箱
        String[] toArr = StrUtil.split(toEmail, ",");

        message.setTo(toArr);
        // 邮件主题
        message.setSubject(subject);
        // 邮件内容
        message.setText(text);
        this.javaMailSender.send(message);
    }


    @Override
    public void html(String toEmail, String subject, String text) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper messageHelper = new MimeMessageHelper(message);

        messageHelper.setFrom(this.mailProperties.getUsername());
        messageHelper.setSubject(subject);
        // 第二个参数表示是否html，设为true
        messageHelper.setText(text, true);

        this.javaMailSender.send(message);
    }


    @Override
    public void attach(String toEmail, String cc, String subject, String text, String attachmentFilename, File file) throws MessagingException {
        MimeMessage message = this.javaMailSender.createMimeMessage();
        // 第二个参数表示是否开启multipart模式
        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);

        messageHelper.setFrom(this.mailProperties.getUsername());
        String[] toArr = StrUtil.split(toEmail, ",");
        messageHelper.setTo(toArr);
        messageHelper.setSubject(subject);
        messageHelper.setCc(cc);
        // 第二个参数表示是否html，设为true
        messageHelper.setText(text, true);
        try {
            messageHelper.addAttachment(MimeUtility.encodeWord(attachmentFilename, "utf-8", "B"), file);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.javaMailSender.send(message);
    }

}