package com.bank.server.application.controller;

import com.bank.server.application.entity.Email;
import com.bank.server.application.entity.Otp;
import com.bank.server.application.service.impl.EmailServiceimpl;
import com.bank.server.application.service.impl.TransactionserviceImpl;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class EmailController {

    @Autowired
    EmailServiceimpl emailServiceimpl;

    @Autowired
    TransactionserviceImpl transactionserviceimpl;
    @PostMapping
    public Email sendEmail(Email email) throws MessagingException {
        emailServiceimpl.sendEmail(email.getTo(),email.getSubject(),email.getMessage());
        return  null;
    }

    @GetMapping("/verifyotp")
    public ResponseEntity<?> verifyotp(@RequestBody Otp otp) throws MessagingException {
        System.out.println(otp.getMyOtp());
        boolean b = transactionserviceimpl.verifyotp(otp.getMyOtp());
        if (b){

            return ResponseEntity.ok("Transaction Successful");
        }
        return ResponseEntity.ok("Transaction fail");
    }


}
