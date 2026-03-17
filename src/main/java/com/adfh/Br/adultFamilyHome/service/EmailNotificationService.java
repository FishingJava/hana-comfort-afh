package com.adfh.Br.adultFamilyHome.service;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailNotificationService {

    @Value("${RESEND_API_KEY}")
    private String apiKey;

    private static final String FROM = "onboarding@resend.dev";

    // 📩 Send to YOU
    public void sendToAdmin(String to, String subject, String body, String replyTo) throws ResendException {
        Resend resend = new Resend(apiKey);

        CreateEmailOptions email = CreateEmailOptions.builder()
                .from(FROM)
                .to(to)
                .subject(subject)
                .text(body)
                .replyTo(replyTo)
                .build();

        resend.emails().send(email);
    }

    // 📩 Send to USER
    public void sendToUser(String to, String subject, String body) throws ResendException {
        Resend resend = new Resend(apiKey);

        CreateEmailOptions email = CreateEmailOptions.builder()
                .from(FROM)
                .to(to)
                .subject(subject)
                .text(body)
                .build();

        resend.emails().send(email);
    }
}