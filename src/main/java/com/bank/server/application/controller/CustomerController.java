package com.bank.server.application.controller;

import com.bank.server.application.Security.Authentication;
import com.bank.server.application.entity.*;
import com.bank.server.application.repository.BankdetailsRepository;
import com.bank.server.application.service.impl.*;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerServiceimpl;

    @Autowired
    TransactionserviceImpl transactionserviceimpl;

    @Autowired
    Accountserviceimpl accountserviceimpl;

    private static Customer cuu;

    private static Customer cre;

    @Autowired
    EmailServiceimpl emailServiceimpl;


    @Autowired
    Authentication authentication;
    @PostMapping("/addCustomer")
    public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) throws MessagingException {
         String msg="Customer Registered Successfully";

         cre= customerServiceimpl.addCustomer(customer);
        emailServiceimpl.sendEmail(customer.getCustomerEmail(),msg,null);
        return ResponseEntity.status(HttpStatus.CREATED).body(cre);

    }
    @PostMapping("/addAccount")
    public ResponseEntity<Account> addAccount(@RequestBody Account account) throws MessagingException {
        String msg="Account Created Successfully";
        boolean b= authentication.authorise();


        if(b){

                if(cuu.getEnable()) {
                    Account acc = customerServiceimpl.addAccount(account);
                    emailServiceimpl.sendEmail(cre.getCustomerEmail(),msg,null);
                    return ResponseEntity.status(HttpStatus.CREATED).body(acc);
                }

        }

        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }

    @PostMapping("/transactionadd")
    public Transaction addtransaction(@RequestBody Transaction transaction) throws MessagingException {
        String msg="Amount "+transaction.getTransactionAmount()+"has been CREDITED to your bank account";
        Transaction trr=transactionserviceimpl.addtransaction(transaction);
        emailServiceimpl.sendEmail(cre.getCustomerEmail(),msg,null);
        return trr;
    }
    @PostMapping("/transactionsub")
    public Transaction subtransaction(@RequestBody Transaction transaction) throws MessagingException {
       // String msg="Amount "+transaction.getTransactionAmount()+"has been DEBITED from your bank account";
        boolean b=authentication.authorise();
        if(b) {
            if(cuu.getEnable()) {
                Transaction trr = transactionserviceimpl.subtransaction(transaction,cre);
               // emailServiceimpl.sendEmail(cre.getCustomerEmail(),msg,null);
                return trr;
            }
        }
        return null;
    }

    @PostMapping("/verifyotp")
    public boolean verifyotp(@RequestBody Otp otp)
    {
       return emailServiceimpl.verifyotp(otp.getMyOtp());
    }

    @PostMapping("/login")
    public ResponseEntity<Customer> login(@RequestBody Customer customer)
    {
        Customer cu=customerServiceimpl.cheak(customer.getCustomerEmail(), customer.getCustomerPassword());

        if (cu!=null)
        {
            authentication.setEmail(cu.getCustomerEmail());
            authentication.setPassword(cu.getCustomerPassword());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(cu);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
    }

    @GetMapping("/customer/{id}")
    public Customer getcustomer(@PathVariable Integer id)
    {
       return customerServiceimpl.getcustomer(id);
    }

    @GetMapping("/allcustomers")
    public List<Customer> getallcustomers()
    {
        return customerServiceimpl.getallcustomers();
    }

    @PostMapping("/updatecustomer")
    public Customer updatecustomer(@RequestBody Customer customer) throws MessagingException {
        String msg="your account has been updated "+customer;
        emailServiceimpl.sendEmail(cre.getCustomerEmail(),msg,null);
      return  customerServiceimpl.updatecustomer(customer.getCustomerID(),customer);
    }

    @PostMapping("/enableaccount")
    public Boolean enable(@RequestBody Customer customer) throws MessagingException {
         cuu= customerServiceimpl.enable(customer.getCustomerID(),customer.getEnable());
        if (cuu!=null)
        {
            String msg="your account has been enabled ";
            emailServiceimpl.sendEmail(customer.getCustomerEmail(),msg,"Account enabled");
            return true;
        }
        return false;
    }
    @PostMapping("/disableaccount")
    public ResponseEntity<Boolean> disable(@RequestBody Customer customer) throws MessagingException {
        System.out.println(customer.getCustomerID());
        boolean b= customerServiceimpl.disable(customer.getCustomerID());
        if (b)
        {
            String msg="your account has been disabled with account id"+customer.getCustomerID()+" Contact Bank to enable again "+"Customer details"+customer;
            emailServiceimpl.sendEmail(customer.getCustomerEmail(),msg,"Conatct Bank ASAP");
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(true);
        }
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(false);
    }

}
