package com.khushee.restfulwebservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
   private int id;
   private String accountNumber;
    private String accountName;
   private String profile;
   private int pin;
   private String password;
   private String phoneNumber;
   private int transferLimit;
   private AccountType accountType;

}
