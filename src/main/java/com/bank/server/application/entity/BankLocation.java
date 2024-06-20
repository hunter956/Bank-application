package com.bank.server.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class BankLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bankLocationId;
    private String city;
    private String state;
    private String country;

   @JsonBackReference
    @OneToOne
    BankDetails bankDetails;
}
