package com.bank.server.application.repository;

import com.bank.server.application.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account,Integer> {

    public Account getAccountByaccountID(Integer accountID);
}
