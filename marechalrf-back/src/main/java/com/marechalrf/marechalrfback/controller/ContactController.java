package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.dto.response.Response;
import com.marechalrf.marechalrfback.model.Contact;
import com.marechalrf.marechalrfback.service.ContactService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.Callable;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Response<Contact>> submitContactForm(@RequestBody Contact contact) {
        try {
            Contact savedContact = contactService.saveContact(contact);
            if (savedContact != null) {
                Response<Contact> response = new Response<>();
                response.setData(savedContact);
                response.setSuccess(true);
                return ResponseEntity.ok(response);
            } else {
                LOGGER.error("Failed to save contact, service returned null");
                Response<Contact> response = new Response<>();
                response.setMessage("Failed to save contact");
                response.setSuccess(false);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        } catch (Exception e) {
            LOGGER.error("Error saving contact", e);
            Response<Contact> response = new Response<>();
            response.setMessage("Error saving contact: " + e.getMessage());
            response.setSuccess(false);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
/*
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
 */