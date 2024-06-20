package com.bank.server.application.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.mapping.PrimaryKey;

import java.util.List;

@Entity
@Data
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountID;
    @Column(unique = true)
    private long accountNo;
    private float accountBalance;

    @JsonBackReference
    @OneToOne
    Customer customer;

    @JsonManagedReference
    @OneToOne
    @JoinColumn(name = "bankdetail_Id")
    BankDetails bankDetails;

    @JsonManagedReference
    @OneToMany(mappedBy = "accounts",cascade = CascadeType.REMOVE)
    private List<Transaction> transaction;
}
