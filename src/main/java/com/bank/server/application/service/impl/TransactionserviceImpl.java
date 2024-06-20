package com.bank.server.application.service.impl;

import com.bank.server.application.entity.Account;
import com.bank.server.application.entity.Customer;
import com.bank.server.application.entity.Transaction;
import com.bank.server.application.repository.AccountRepository;
import com.bank.server.application.repository.TransactionRepository;
import com.bank.server.application.service.TransactionService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TransactionserviceImpl implements TransactionService {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    HttpSession httpSession;

    @Autowired
    EmailServiceimpl emailServiceimpl;

    private static Customer cre;

    @Override
    public Transaction addtransaction(Transaction transaction) {
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionTime(LocalDateTime.now());


        Long accountID= (long) transaction.getAccounts().getAccountID();
        Account a=accountRepository.getById(Math.toIntExact(accountID));
        Float availableAmount=a.getAccountBalance();

        if(Objects.equals(transaction.getTransactionStatus(), "CREDITED"))
        {
            float totalAmount=availableAmount+transaction.getTransactionAmount();
            a.setAccountBalance(totalAmount);
            accountRepository.save(a);
            return transactionRepository.save(transaction);
        }
        return null;
    }

    @Override
    public Transaction subtransaction(Transaction transaction, Customer cra) {
        transaction.setTransactionDate(LocalDate.now());
        transaction.setTransactionTime(LocalDateTime.now());

        cre=cra;
        Long accountID= (long) transaction.getAccounts().getAccountID();
        Account a=accountRepository.getById(Math.toIntExact(accountID));
        Float availableAmount=a.getAccountBalance();

        if(Objects.equals(transaction.getTransactionStatus(), "DEBITED"))
        {

            float totalAmount=availableAmount-transaction.getTransactionAmount();
            a.setAccountBalance(totalAmount);

            if(availableAmount>=transaction.getTransactionAmount()){

            httpSession.setAttribute("account",a);
            httpSession.setAttribute("transaction",transaction);
            emailServiceimpl.sendotp(a.getCustomer().getCustomerEmail());
            /*accountRepository.save(a);

            return  transactionRepository.save(transaction);*/
                return null;

            }
        }

        return null;
    }

    @Override
    public boolean verifyotp(String otp) throws MessagingException {

        Account account= (Account) httpSession.getAttribute("account");
        Transaction transaction= (Transaction) httpSession.getAttribute("transaction");
        String msg="Amount "+transaction.getTransactionAmount()+"has been DEBITED from your bank account";
        boolean b = emailServiceimpl.verifyotp(otp);
        if(b){
            accountRepository.save(account);
            transactionRepository.save(transaction);
            emailServiceimpl.sendEmail(cre.getCustomerEmail(),msg,null);
            return true;
        }
        return false;
    }
}
