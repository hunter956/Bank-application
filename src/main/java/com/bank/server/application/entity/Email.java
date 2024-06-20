package com.bank.server.application.entity;

import lombok.Data;

@Data
public class Email {
    private String to;
    private String message;
    private String subject;
}
