package com.bank.server.application.service.impl;

import com.bank.server.application.Security.Authentication;
import com.bank.server.application.entity.Account;
import com.bank.server.application.entity.Customer;
import com.bank.server.application.repository.AccountRepository;
import com.bank.server.application.repository.CustomerRepository;
import com.bank.server.application.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    AccountRepository accountRepository;




    @Override
    public Customer addCustomer(Customer customer) {
        customer.setLoginDate(LocalDateTime.now());
        customer.setLogoutTime(LocalDateTime.now());
        customerRepository.save(customer);
        return customer;
    }

    @Override
    public Account addAccount(Account account) {

        accountRepository.save(account);
        return account;
    }

    @Override
    public void deletecustomer(Integer id) {
        customerRepository.deleteCustomerByCustomerID(id);

    }

    @Override
    public Customer cheak(String Email, String Password) {
      Customer cu=  customerRepository.getCustomerByCustomerEmailAndCustomerPassword(Email, Password);
        return cu;
    }

    @Override
    public Customer getcustomer(Integer id) {
       return customerRepository.getCustomerByCustomerID(id);

    }

    @Override
    public Customer updatecustomer(Integer customerId,Customer customer) {
        Customer updatedCustomer=customerRepository.getCustomerByCustomerID(customerId);
        if (updatedCustomer!=null)
        {
            updatedCustomer.setCustomerName(customer.getCustomerName());
            updatedCustomer.setCustomerEmail(customer.getCustomerEmail());
            updatedCustomer.setCustomerContact(customer.getCustomerContact());
            updatedCustomer.setCustomerPassword(customer.getCustomerPassword());

            customerRepository.save(updatedCustomer);
            return updatedCustomer;
        }

        return null;
    }

    @Override
    public Customer enable(Integer customerID, Boolean enable) {
        Customer enablecustomer= customerRepository.getCustomerByCustomerID(customerID);

        if (enablecustomer!=null) {
            enablecustomer.setEnable(enable);
           customerRepository.save(enablecustomer);
            return enablecustomer;
        }
        return null;
    }

    @Override
    public List<Customer> getallcustomers() {
       List allcustomer= customerRepository.findAll();
        return allcustomer;
    }

    @Override
    public boolean disable(int customerID) {
       Customer customer= customerRepository.getCustomerByCustomerID(customerID);
       customer.setEnable(false);
       customerRepository.save(customer);
        return true;
    }


}
