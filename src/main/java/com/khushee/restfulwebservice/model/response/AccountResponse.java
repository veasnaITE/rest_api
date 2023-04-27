package com.khushee.restfulwebservice.model.response;
import com.khushee.restfulwebservice.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {
    private int id;
    private String accountNumber;
    private String accountName;
    private String profile;
    private String phoneNumber;
    private int transferLimit;
    private AccountType accountType;
}

