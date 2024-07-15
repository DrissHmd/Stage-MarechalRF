package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.model.ContactForm;
import com.marechalrf.marechalrfback.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ContactController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/contact")
    public ResponseEntity<String> sendContactEmail(@RequestBody ContactForm contactForm) {
        String emailContent = "Nom: " + contactForm.getNom() + "\n" +
                "Prénom: " + contactForm.getPrenom() + "\n" +
                "Entreprise: " + contactForm.getEntreprise() + "\n" +
                "Fonction: " + contactForm.getFonction() + "\n" +
                "Email: " + contactForm.getEmail() + "\n" +
                "Téléphone: " + contactForm.getTelephone() + "\n" +
                "Message: " + contactForm.getMessage() + "\n" +
                "Consentement: " + (contactForm.isConsent() ? "Oui" : "Non");
        emailService.sendSimpleMessage("marechalretf@gmail.com", "Nouveau message de contact", emailContent);
        return ResponseEntity.ok("Message envoyé avec succès");
    }


}
