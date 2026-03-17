package com.adfh.Br.adultFamilyHome.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ContactController {

    private final EmailNotificationService emailService;

    @PostMapping("/contact")
    public String handleContactForm(@RequestBody Map<String, String> formData){
        String name = formData.get("name");
        String email = formData.get("email");
        String phone = formData.get("phone");
        String message = formData.get("message");

        String subject = "New Contact Message from " + name;
        String body = "Name: " + name +  "\nPhone: " + phone + "\nEmail: " + email + "\n\nMessage:\n" + message;

        // Send the email to your admin inbox
        emailService.send("hcomfortafh@gmail.com", subject, body);

        // Optional: send confirmation email to the user
        emailService.send(email,
                "Thank you for contacting Hana comfort AFH \n",
                "Hello, " + name + ",\n" + "We've received your message and will get back to you soon.\n" + "\nThank you!" + "\nHaha Comfort - Adult Family Home");


        return "redirect:/?success=true";
    }
}