package com.codingnfun.messaging.controller;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

@Configuration
public class MailController implements CommandLineRunner {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void run(String... args) throws Exception {
        //System.out.println("Sending Mail....");
        //sendEmailWithAttachement();
        //System.out.println("Done");
    }

    private void sendEmail() {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("codingnfun2025@gmail.com");
        msg.setSubject("Email send using condingNfun");
        msg.setText("Hi! \n\n This is from spring boot");
        javaMailSender.send(msg);
    }
    private void sendEmailWithAttachement() throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setTo("codingnfun2025@gmail.com");
        mimeMessageHelper.setSubject("Email send using condingNfun with attachment");
        mimeMessageHelper.setText("<h3>Hi! \n\n This is from spring boot</h3>", true);
        mimeMessageHelper.addAttachment("w.jpg", new ClassPathResource("/static/w.jpg"));
        javaMailSender.send(mimeMessage);
    }
}
