package com.fiap.backend.model;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class EmailSummaryDTO {
    private UUID id;
    private String from;
    private Date date;
    private String subject;

    private String groupName;
    private String color;

    public EmailSummaryDTO(UUID id, String from, Date date, String subject) {
        this.id = id;
        this.from = from;
        this.date = date;
        this.subject = subject;
    }

}
