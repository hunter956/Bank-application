package com.bank.server.application.service.impl;

import com.bank.server.application.entity.BankDetails;
import com.bank.server.application.entity.BankLocation;
import com.bank.server.application.entity.Customer;
import com.bank.server.application.entity.Transaction;
import com.bank.server.application.repository.BankLocationRepository;
import com.bank.server.application.repository.BankdetailsRepository;
import com.bank.server.application.repository.CustomerRepository;
import com.bank.server.application.repository.TransactionRepository;
import com.bank.server.application.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    BankdetailsRepository bankdetailsRepository;

    @Autowired
    BankLocationRepository bankLocationRepository;

    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public BankDetails addbankdetails(BankDetails bankDetails) {
     BankDetails bak=  bankdetailsRepository.save(bankDetails);
        return bak;
    }

    @Override
    public BankLocation addbanklocation(BankLocation bankLocation) {

        return bankLocationRepository.save(bankLocation);
    }



    @Override
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }
}
