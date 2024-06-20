package com.bank.server.application.service.impl;

import com.bank.server.application.entity.Account;
import com.bank.server.application.repository.AccountRepository;
import com.bank.server.application.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Accountserviceimpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

}
