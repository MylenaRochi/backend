package com.fiap.backend.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class ReceivedEmail {

    private UUID id;
    private String from;
    private String to;
    private String subject;
    private String body;
    private Date receivedDate;
    private List<String> cc;
    private List<String> bcc;
    private boolean read;

    public ReceivedEmail() {
    }

    public ReceivedEmail(UUID id, String from, String to, String subject, String body, Date receivedDate, List<String> cc, List<String> bcc, boolean read) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
        this.receivedDate = receivedDate;
        this.cc = cc;
        this.bcc = bcc;
        this.read = read;
    }
}

