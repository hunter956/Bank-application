package com.bank.server.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int transactionId;
    private float transactionAmount;
    private String transactionStatus;
    private LocalDate transactionDate;
    private LocalDateTime transactionTime;

    @JsonBackReference
    @ManyToOne
    Account accounts;

}
