package com.bank.server.application.service;

import jakarta.mail.MessagingException;

public interface EmailService {
    public void sendEmail(String to, String subject, String message) throws MessagingException;

    public Integer sendotp(String email);
    public boolean verifyotp(String otp);
}
