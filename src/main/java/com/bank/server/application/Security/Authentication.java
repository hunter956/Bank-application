package com.bank.server.application.Security;

import com.bank.server.application.entity.Customer;
import com.bank.server.application.service.CustomerService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Data
@Component
public class Authentication {
    @Autowired
    CustomerService customerService;
    private String email;
    private String password;
    public boolean authorise()
    {
        boolean b=false;
       String Email= getEmail();
       String Password=getPassword();
      Customer c= customerService.cheak(Email,Password);
      if (c!=null) {
         return b=true;
      }
      return b;
    }
}
