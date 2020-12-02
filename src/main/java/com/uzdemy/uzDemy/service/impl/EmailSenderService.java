package com.uzdemy.uzDemy.service.impl;


import com.uzdemy.uzDemy.model.ConfirmationCode;
import com.uzdemy.uzDemy.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSenderService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(User user, ConfirmationCode confirmationCode){
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(user.getEmail());
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setFrom("xaridtest@gmail.com");
        mailMessage.setText("To confirm your account, please click here : "
                +"http://localhost:8080/api/auth/confirm-account?code="+ confirmationCode.getConfirmationCode());

        javaMailSender.send(mailMessage);
    }
}
