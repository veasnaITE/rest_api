package com.khushee.restfulwebservice.service;

import com.khushee.restfulwebservice.model.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccounts();
    int createAccount(Account account);
    int updateAccount(Account account,int id);
    Account findAccountByID(int id);
}
