package com.bank.server.application.service;

import com.bank.server.application.entity.Account;
import com.bank.server.application.entity.Customer;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CustomerService {
    public Customer addCustomer(Customer customer);

   public  Account addAccount(Account account);


   public void deletecustomer(Integer id);

    public Customer cheak(String Email,String Password);

   public Customer getcustomer(Integer id);

   public Customer updatecustomer(Integer customerId,Customer customer);

   public Customer enable(Integer customerID, Boolean enable);

    public List<Customer> getallcustomers();

    public boolean disable(int customerID);
}
