package com.fiap.backend.sevices;

import com.fiap.backend.model.EmailSummaryDTO;
import com.fiap.backend.model.ReceivedEmail;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailService {
    public List<EmailSummaryDTO> getEmailSummaries(String emailAddress);

    public Optional<ReceivedEmail> getEmailById(UUID id);
}
