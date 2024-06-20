package com.bank.server.application.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customerID;
    private String customerName;
    @Column(unique = true)
    private String customerContact;
    @Column(unique = true)
    private String customerEmail;
    private String customerPassword;
    private LocalDateTime loginDate;
    private LocalDateTime logoutTime;
    private Boolean enable;
    private String role;
    @JsonManagedReference
    @OneToOne(mappedBy = "customer",cascade = CascadeType.REMOVE,orphanRemoval = true)
     Account account;

}
