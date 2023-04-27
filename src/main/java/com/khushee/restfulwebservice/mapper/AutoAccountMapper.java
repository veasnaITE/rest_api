package com.khushee.restfulwebservice.mapper;

import com.khushee.restfulwebservice.model.Account;
import com.khushee.restfulwebservice.model.response.AccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutoAccountMapper {
  //account -> accountresponse
    List<AccountResponse> mapToAccountResponse(List<Account>accounts);
  //accountresponse -> account
    List<Account>matToAccount(List<AccountResponse>responses);
}
