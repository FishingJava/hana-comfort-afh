package com.adfh.Br.adultFamilyHome.controller;

import com.adfh.Br.adultFamilyHome.service.EmailNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final EmailNotificationService emailService;

    @PostMapping("/contact")
    public String handleContactForm(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam(required = false) String phone,
            @RequestParam String message) {

        String subject = "New Contact Message from " + name;

        String adminBody =
                "Name: " + name +
                        "\nPhone: " + phone +
                        "\nEmail: " + email +
                        "\n\nMessage:\n" + message;

        try {
            // 📩 Send email TO YOU
            emailService.sendToAdmin(
                    "hcomfortafh@gmail.com",
                    subject,
                    adminBody,
                    email   // reply-to user
            );

            // 📩 Send confirmation TO USER
            emailService.sendToUser(
                    email,
                    "Thank you for contacting Hana Comfort AFH",
                    "Hello " + name + ",\n\n" +
                            "We received your message and will contact you soon.\n\n" +
                            "Thank you!"
            );

            return "redirect:/?success=true";

        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/?error=true";
        }
    }
}