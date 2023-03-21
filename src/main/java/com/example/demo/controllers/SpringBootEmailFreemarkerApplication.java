package com.example.demo.controllers;

import com.example.demo.Dto.MailRequest;
import com.example.demo.Dto.MailResponse;
import com.example.demo.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
public class SpringBootEmailFreemarkerApplication {

    @Autowired
    private EmailService service ;

    @PostMapping("/sendingEmail")
    public MailResponse sendEmail(@RequestBody MailRequest request) {
        Map<String, Object> model = new HashMap<>();
        model.put("Name", request.getName());
        model.put("location", "Bangalore,India");
        return service.sendEmail(request, model);

    }}