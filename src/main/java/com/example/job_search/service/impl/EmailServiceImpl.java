package com.example.job_search.service.impl;

import com.example.job_search.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private  final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String emailForm;

    @Override
    public void send(String to, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");

        helper.setFrom(emailForm, "Job Search Support");
        helper.setTo(to);
        helper.setSubject("Восстановление пароля - JobSearch");


        String content = "<p>Здравствуйте!</p>" +
                "<p>Вы запросили сброс пароля для вашего аккаунта на Job Search.</p>" +
                "<p>Нажмите на кнопку ниже, чтобы задать новый пароль:</p>" +
                "<p> <a href=\"%s\" Сбросить пароль </a> </p>  <br>" +
                "<p>Если вы не запрашивали сброс пароля — просто проигнорируйте это письмо.</p>\n" +
                "<p>Ссылка действительна в течение 24 часов.</p>";

        helper.setText(content.formatted(link), true);
        mailSender.send(message);

    }
}
