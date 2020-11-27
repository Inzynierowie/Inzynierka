package com.engineering.thesis.backend.service;

public interface EmailSenderService {
    void sendEmail(String to, String subject, String content);
}
