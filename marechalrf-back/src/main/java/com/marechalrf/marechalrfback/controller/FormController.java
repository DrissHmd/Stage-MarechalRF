package com.marechalrf.marechalrfback.controller;

import com.marechalrf.marechalrfback.model.FormData;
import com.marechalrf.marechalrfback.service.FormService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FormController {

    @Autowired
    private FormService formService;

    @PostMapping("/form-submit")
    public ResponseEntity<?> submitForm(@Valid @RequestBody FormData formData) {
        FormData savedFormData = formService.saveFormData(formData);
        return ResponseEntity.ok(savedFormData);
    }
}