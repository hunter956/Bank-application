package com.bank.server.application.service.impl;

import com.bank.server.application.entity.BankLocation;
import com.bank.server.application.repository.BankLocationRepository;
import com.bank.server.application.repository.BankdetailsRepository;
import com.bank.server.application.service.BankLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankLocationServiceimpl implements BankLocationService {

    @Autowired
    BankLocationRepository bankLocationRepository;

    @Override
    public BankLocation updatebanklocation(Integer bankLocationId, BankLocation bankLocation) {
      BankLocation updatebanklocation= bankLocationRepository.getBankLocationByBankLocationId(bankLocationId);
       updatebanklocation.setCity(bankLocation.getCity());
       updatebanklocation.setState(bankLocation.getState());
       updatebanklocation.setCountry(bankLocation.getCountry());
        return bankLocationRepository.save(updatebanklocation);
    }
}
