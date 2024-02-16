package com.ms.email.service;

import com.ms.email.models.EmailModel;
import com.ms.email.models.StatusEmail;
import com.ms.email.repository.EmailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final EmailRepository emailRepository;

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String emailFrom;

    @Transactional
    public EmailModel sendEmail(EmailModel model) {
        try {
            model.setSendDateEmail(LocalDateTime.now());
            model.setEmailFrom(emailFrom);

            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(model.getEmailFrom());
            message.setSubject(model.getSubject());
            message.setText(model.getText());
            message.setTo(model.getEmailTo());

            emailSender.send(message);
        } catch (MailException exception) {
            exception.printStackTrace();
            model.setStatusEmail(StatusEmail.ERROR);
        } finally {
            model.setStatusEmail(StatusEmail.SENT);
            return emailRepository.save(model);
        }
    }

}
