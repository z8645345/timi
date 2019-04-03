package com.timi.timizhuo.util;

import com.timi.timizhuo.dto.request.EmaillDto;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmaillUtils {
    private static String host = "smtp.163.com";
    private static String port = "25";
    private static String userName = "timizhuo_timi@163.com";
    private static String password = "zengjia950415";

    /**
     * 发送文本邮件
     *
     * @throws Exception
     */
    public static void sendTextMail(EmaillDto emaillDto) throws Exception {
        Properties pro = System.getProperties();
        pro.put("mail.smtp.host", host);
        pro.put("mail.smtp.port", port);
        pro.put("mail.smtp.auth", "true");

        // 根据邮件会话属性和密码验证器构造一个发送邮件的session
        Session sendMailSession = Session.getDefaultInstance(pro,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, password);
                    }
                });
        // 根据session创建一个邮件消息
        Message mailMessage = new MimeMessage(sendMailSession);
        // 设置邮件消息的发送者
        mailMessage.setFrom(new InternetAddress(userName));
        // 创建邮件的接收者地址，并设置到邮件消息中
        mailMessage.setRecipient(Message.RecipientType.TO,
                new InternetAddress(emaillDto.getTo()));
        // 设置邮件消息的主题
        mailMessage.setSubject(emaillDto.getTitle());
        // 设置邮件消息发送的时间
        mailMessage.setSentDate(new Date());
        // 设置邮件消息的主要内容
        mailMessage.setText(emaillDto.getText());
        // 发送邮件
        Transport.send(mailMessage);
    }
}