package com.bank.server.application.repository;

import com.bank.server.application.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
@Transactional
 public Integer deleteCustomerByCustomerID(Integer CustomerID);


public Customer getCustomerByCustomerEmailAndCustomerPassword(String customerEmail,String customerPassword);

public Customer getCustomerByCustomerID(Integer CustomerID);


}
