package com.example.job_search.service.impl;

import com.example.job_search.exception.UserNotFoundException;
import com.example.job_search.model.User;
import com.example.job_search.repository.UserRepository;
import com.example.job_search.service.EmailService;
import com.example.job_search.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void sendResetEmail(String email, String siteUrl) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException("Пользователь не найден"));

        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        userRepository.save(user);

        String resetUrl = siteUrl + "/auth/reset-password?token=" + token;

        try{
            emailService.send(email, resetUrl);
            log.info("Письмо для сброса пароля отправлено на: {}", email);
        }catch (Exception e){
            log.error("Ошибка при отправке письма на {}: {}", email, e.getMessage());
            throw new RuntimeException("Не удалось отправить письмо", e);
        }
    }

    @Override
    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetPasswordToken(token)
                .orElseThrow(() -> new UserNotFoundException("Неверный или устаревший токен"));

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);
        userRepository.save(user);
        log.info("Пароль успешно сброшен для пользователя: {}", user.getEmail());
    }
}
