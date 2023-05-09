package com.khushee.restfulwebservice.controller;
import com.github.pagehelper.PageInfo;
import com.khushee.restfulwebservice.model.Transaction;
import com.khushee.restfulwebservice.model.User;
import com.khushee.restfulwebservice.model.UserTransaction;
import com.khushee.restfulwebservice.model.resquest.UserRequest;
import com.khushee.restfulwebservice.service.TransactionService;
import com.khushee.restfulwebservice.utils.Response;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionRestController {
private final TransactionService transactionService;
TransactionRestController(TransactionService transactionService){
    this.transactionService=transactionService;
}
@GetMapping("/all-transaction")
public Response<PageInfo<Transaction>>getAllTransactions(@RequestParam (defaultValue = "1")int page,@RequestParam(defaultValue = "2") int size){
    try{
        PageInfo<Transaction>transactionPageInfo= transactionService.getAllTransactions(page, size);
        return Response.<PageInfo<Transaction>>ok().setPayload(transactionPageInfo).setMessage("Successfully retrieved All transaction");
    }catch (Exception e){
        return Response.<PageInfo<Transaction>>exception().setMessage("Filed to retrieved Transaction");
    }
}

@GetMapping("/{id}")
public Response<List<Transaction>>findTransactionByID(@PathVariable int id){
    try{
        List<Transaction> response= transactionService.getTransactionByUserID(id);
        if(response != null){
            return Response.<List<Transaction>>ok().setPayload(response).setSuccess(true).setMessage("Successfully Retrieved user with id= "+id);

        }else {
            return Response.<List<Transaction>>notFound().setMessage("User with id= "+id+ "doesn't exist").setSuccess(false);
        }
    }catch (Exception ex){
        return Response.<List<Transaction>>exception().setMessage("Failed to retrieved user with id: "+id );
    }
}

    @DeleteMapping("/{id}")
    public Response<?> deleteTransactionById(@PathVariable int id){
        try {
            int deleteTran = transactionService.deleteTransaction(id);
            if (deleteTran>0){
                return Response.<Transaction>deleteSuccess().setMessage("Your Transaction is Successfully removed!!!").setSuccess(true);
            }else
                return Response.<Transaction>notFound().setMessage("Undefined Your Transaction Id = "+id+"to removed");
        }catch (Exception e){
            return Response.<Transaction>exception().setMessage("Exception Occurs!!!!Failed to remove the Transaction").setSuccess(false);
        }
    }




}

