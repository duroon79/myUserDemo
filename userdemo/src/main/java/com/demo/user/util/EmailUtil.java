/*
 * Copyright (c) 2022. Owned by Squel. All rights reserved
 */

package com.demo.user.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.stereotype.Component;



/**
 * @ClassName EmailUtil
 * @Description 邮件工具类
 * @Author SQLUO
 * @Date 2022/5/22
 * Version 1.0
 **/
@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender javaMailSender;

    /**
     * 发送邮件
     * @param mailFrom 发件人
     * @param mailTo 收件人
     * @param subject 邮件标题
     * @param text 邮件内容
     */
    public void sendEmail(String mailFrom,String mailTo,String subject,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mailFrom);
        message.setTo(mailTo);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

}
