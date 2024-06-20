package com.bank.server.application.service;

import com.bank.server.application.entity.BankDetails;
import com.bank.server.application.entity.BankLocation;
import com.bank.server.application.entity.Customer;
import com.bank.server.application.entity.Transaction;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AdminService {
   public BankDetails addbankdetails(BankDetails bankDetails);

   public BankLocation addbanklocation(BankLocation bankLocation);



    public List<Customer> getCustomer();
}
