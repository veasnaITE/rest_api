package com.khushee.restfulwebservice.service.serviceImpl;

import com.khushee.restfulwebservice.model.Account;
import com.khushee.restfulwebservice.repository.AccountRepository;
import com.khushee.restfulwebservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AccountServiceImpl implements AccountService {
    //inject Repost
    final private AccountRepository accountRepository;
    @Autowired
    AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository=accountRepository;
    }


    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.getAllAccounts();
    }

    @Override
    public int createAccount(Account account) {
        return 0;
    }

    @Override
    public int updateAccount(Account account, int id) {
        return 0;
    }

    @Override
    public Account findAccountByID(int id) {
        return null;
    }

}
