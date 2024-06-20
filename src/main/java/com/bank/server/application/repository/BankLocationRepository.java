package com.bank.server.application.repository;

import com.bank.server.application.entity.BankLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankLocationRepository extends JpaRepository<BankLocation,Integer> {

    public BankLocation getBankLocationByBankLocationId(Integer bankLocationId);
}
