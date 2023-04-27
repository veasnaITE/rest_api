package com.khushee.restfulwebservice.controller;

import com.khushee.restfulwebservice.mapper.AutoAccountMapper;
import com.khushee.restfulwebservice.model.Account;
import com.khushee.restfulwebservice.model.response.AccountResponse;
import com.khushee.restfulwebservice.service.AccountService;
import com.khushee.restfulwebservice.utils.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {
    //inject AccountService
    private final AccountService accountService;
    private final AutoAccountMapper autoAccountMapper;
    AccountController(AccountService accountService,AutoAccountMapper autoAccountMapper){
        this.accountService=accountService;
        this.autoAccountMapper=autoAccountMapper;
    }
    //using Respone Entity
    @GetMapping("/all-accounts")
        public Response<List<AccountResponse>> getAllAccount(){
            try{
                List<Account>allAccount= accountService.getAllAccounts();
                //System.out.println(allAccount);
                //modelmapper , mapstruct
//                List<Account>allAccount = accountService.getAllAccounts();
                List<AccountResponse>accountResponses = autoAccountMapper.mapToAccountResponse(allAccount);

                return Response.<List<AccountResponse>>ok().setPayload(accountResponses).setMessage("Success fully Retrieved data allacount information ");
            }catch (Exception ex){
                System.out.println("Something When Wrong"+ex.getMessage());

                return Response.<List<AccountResponse>>exception().setMessage("Exception occure! Fail to rerived data account informtaion");
            }
        }
}
