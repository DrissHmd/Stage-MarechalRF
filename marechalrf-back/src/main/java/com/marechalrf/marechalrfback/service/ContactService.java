package com.marechalrf.marechalrfback.service;

import com.marechalrf.marechalrfback.model.Contact;
import com.marechalrf.marechalrfback.repository.ContactRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ContactService {

    private static final Logger logger = LoggerFactory.getLogger(ContactService.class);

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Transactional
    public Contact saveContact(Contact contact) {
        logger.debug("Enregistrement du contact : {}", contact);
        try {
            Contact savedContact = contactRepository.save(contact);
            logger.debug("Contact enregistré avec succès : {}", savedContact);
            sendNotificationEmail(savedContact);
            return savedContact;
        } catch (Exception e) {
            logger.error("Erreur lors de l'enregistrement du contact", e);
            throw e;
        }
    }

    private void sendNotificationEmail(Contact contact) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("marechalretf@gmail.com");
        message.setSubject("Nouveau message de contact reçu");
        message.setText("Vous avez reçu un nouveau message de contact de " + contact.getNom() + " " + contact.getPrenom() + ".");
        mailSender.send(message);
    }
}
