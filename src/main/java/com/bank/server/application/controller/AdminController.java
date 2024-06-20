package com.bank.server.application.controller;

import com.bank.server.application.entity.BankDetails;
import com.bank.server.application.entity.BankLocation;
import com.bank.server.application.entity.Customer;
import com.bank.server.application.entity.Transaction;
import com.bank.server.application.service.impl.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
public class AdminController {

    @Autowired
    AdminServiceImpl adminServiceimpl;

    @Autowired
    CustomerServiceImpl customerServiceimpl;

    @Autowired
    BankdetailsServiceimpl bankdetailsServiceimpl;

    @Autowired
    TransactionserviceImpl transactionserviceimpl;

    @Autowired
    BankLocationServiceimpl bankLocationServiceimpl;


    @PostConstruct
    public  void saveadmin()
    {
        Customer cus= new Customer();
        cus.setCustomerID(1);
        cus.setCustomerName("Pruthviraj");
        cus.setCustomerPassword("Pruthviraj@123");
        cus.setCustomerContact("9637789939");
        cus.setCustomerEmail("shreyaspanchal276@gmail.com");
        cus.setRole("Admin");
        cus.setEnable(true);

        customerServiceimpl.addCustomer(cus);
    }


    @PostMapping("/adminBankdetails")
    public ResponseEntity<BankDetails> addbankdetails(@RequestBody BankDetails bankDetails)
    {
       BankDetails bak= adminServiceimpl.addbankdetails(bankDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(bak);
    }

    @PostMapping("/bankloaction")
    public BankLocation addbanklocation(@RequestBody BankLocation bankLocation)
    {
       BankLocation bakk= adminServiceimpl.addbanklocation(bankLocation);
       return bakk;
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomer()
    {
      List<Customer> cuss= adminServiceimpl.getCustomer();
      return ResponseEntity.status(HttpStatus.ACCEPTED).body(cuss);
    }
    @DeleteMapping("/Delete/{id}")
    public void deletecustomer(@PathVariable Integer id)
    {
        customerServiceimpl.deletecustomer(id);

    }
    @PostMapping("/updatbankdetails")
    public BankDetails updatebankdetails(@RequestBody BankDetails bankDetails)
    {
        return bankdetailsServiceimpl.updatebankdetails(bankDetails.getBranchid(),bankDetails);
    }

    @PostMapping("/updatebanklocation")
    public BankLocation updatebanklocation(@RequestBody BankLocation bankLocation)
    {
        return bankLocationServiceimpl.updatebanklocation(bankLocation.getBankLocationId(),bankLocation);
    }
}
