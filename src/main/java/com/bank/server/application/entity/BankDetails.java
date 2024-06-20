package com.bank.server.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class BankDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branchid;
    private String bankName;
    private String bankIFSC;
    @JsonBackReference
    @Transient
   @OneToOne(mappedBy = "bankDetails",cascade = CascadeType.REMOVE)
    Account account;

    @JsonManagedReference
    @OneToOne(mappedBy = "bankDetails",cascade = CascadeType.REMOVE)
    BankLocation bankLocation;

}
