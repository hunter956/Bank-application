package com.bank.server.application.service.impl;

import com.bank.server.application.entity.BankDetails;
import com.bank.server.application.repository.BankdetailsRepository;
import com.bank.server.application.service.BankdetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankdetailsServiceimpl implements BankdetailsService {
   @Autowired
    BankdetailsRepository bankdetailsRepository;
    @Override
    public BankDetails updatebankdetails(Integer branchid, BankDetails bankDetails) {
      BankDetails updatebankdetails=  bankdetailsRepository.getBankDetailsByBranchid(branchid);
      if(updatebankdetails!=null){
          updatebankdetails.setBankName(bankDetails.getBankName());
          updatebankdetails.setBankIFSC(bankDetails.getBankIFSC());
         return bankdetailsRepository.save(updatebankdetails);
      }
        return null;
    }
}
