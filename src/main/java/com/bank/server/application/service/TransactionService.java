package com.bank.server.application.service;

import com.bank.server.application.entity.Customer;
import com.bank.server.application.entity.Transaction;
import jakarta.mail.MessagingException;

public interface TransactionService {
   public Transaction addtransaction(Transaction transaction);


  public Transaction subtransaction(Transaction transaction, Customer cre);

  public boolean verifyotp(String otp) throws MessagingException;
}
