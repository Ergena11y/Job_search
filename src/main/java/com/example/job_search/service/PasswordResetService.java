package com.example.job_search.service;

public interface PasswordResetService {
    void sendResetEmail(String email, String siteUrl);
    void resetPassword(String token, String newPassword);
}
