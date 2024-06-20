package com.bank.server.application.repository;

import com.bank.server.application.entity.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankdetailsRepository extends JpaRepository<BankDetails,Integer> {

    public BankDetails getBankDetailsByBranchid(Integer branchid);
}
