package com.fiap.backend.controller;

import com.fiap.backend.entities.EmailGroup;
import com.fiap.backend.model.EmailSummaryDTO;
import com.fiap.backend.model.ReceivedEmail;
import com.fiap.backend.repositories.EmailGroupRepository;
import com.fiap.backend.repositories.EmailRepository;
import com.fiap.backend.sevices.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class InboxController {

    @Autowired
    private Map<String, EmailService> emailServices;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private EmailGroupRepository emailGroupRepository;

    @GetMapping("/inbox/summary/{userId}")
    public List<EmailSummaryDTO> getEmailSummaries(@PathVariable UUID userId) {
        var emailAddresses = emailRepository.findByUserId(userId);

        List<EmailSummaryDTO> emailSummaries = emailAddresses.stream()
                .flatMap(emailAddress -> {
                    var service = emailServices.get(emailAddress.getEmailType().name());
                    return service.getEmailSummaries(emailAddress.getEmail()).stream();
                })
                .collect(Collectors.toList());

        var emailGroups = emailGroupRepository.findByUserId(userId);

        Map<String, EmailGroup> groupFilters = emailGroups.stream()
                .collect(Collectors.toMap(
                        EmailGroup::getGroupName,
                        group -> group
                ));

        emailSummaries.forEach(em -> {
            groupFilters.values().forEach(emailGroup -> {
                if (emailGroup.getFilterBy().stream().anyMatch(em.getSubject()::contains)) {
                    em.setColor(emailGroup.getColor());
                    em.setGroupName(emailGroup.getGroupName());
                }
            });
        });

        return emailSummaries;
    }

    @GetMapping("/inbox/{id}")
    public ResponseEntity<ReceivedEmail> getEmailById(@PathVariable UUID id) {
        for (EmailService emailService : emailServices.values()) {
            Optional<ReceivedEmail> email = emailService.getEmailById(id);
            if (email.isPresent()) {
                return new ResponseEntity<>(email.get(), HttpStatus.OK);
            }
        }

        return ResponseEntity.notFound().build();
    }
}
