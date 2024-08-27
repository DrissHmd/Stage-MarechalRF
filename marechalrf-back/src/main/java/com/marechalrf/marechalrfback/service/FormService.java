package com.marechalrf.marechalrfback.service;

import com.marechalrf.marechalrfback.dto.EmailRequest;
import com.marechalrf.marechalrfback.model.Contact;
import com.marechalrf.marechalrfback.model.FormData;
import com.marechalrf.marechalrfback.repository.FormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class FormService {

    private final FormRepository formRepository;
    @Autowired
    private JavaMailSender mailSender;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    // Méthode pour sauvegarder les données du formulaire
    public FormData saveFormData(FormData formData) {
        // Sauvegarder les données du formulaire dans la base de données
        FormData savedData = formRepository.save(formData);

        // Envoyer une notification par email après la sauvegarde
        sendNotificationEmail(formData);

        return savedData;
    }

    private void sendNotificationEmail(FormData formData) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("marechalretf@gmail.com");
        message.setSubject("Nouveau message de contact reçu");
        message.setText("Vous avez reçu une nouvelle demande de formation de " + formData.getNom() + " " + formData.getPrenom() + ".");
        mailSender.send(message);
    }
}