package com.khushee.restfulwebservice.repository;

import com.khushee.restfulwebservice.model.Transaction;
import com.khushee.restfulwebservice.model.UserTransaction;
import com.khushee.restfulwebservice.repository.provider.TransactionProvider;
import jakarta.validation.Valid;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
@Mapper
public interface TransactionRepository {
    @SelectProvider(type = TransactionProvider.class, method = "getTransactions")

            @Results({
            @Result(column = "sender_account_id",property = "sender",one = @One(select = "getUserTransactionByID")),
            @Result(column = "receiver_account_id",property = "receiver",one = @One(select = "getUserTransactionByID")),
            @Result(column = "transfer_at",property = "transferAt")
    })
    List<Transaction> getAllTransactions();
    @Select("select a.id, ut.*, a.account_no from user_account_tb\n" +
            "inner join users_tb ut on ut.id = user_account_tb.user_id\n" +
            "inner join account_tb a on a.id = user_account_tb.account_id\n" +
            "where account_id = #{id};")
    @Results(value = {
            @Result(property = "accountId",column = "account_id"),
            @Result(property = "accountNumber",column = "account_no"),
            @Result(property = "user.userId",column = "id"),
            @Result(property = "user.username",column = "username"),
            @Result(property = "user.gender",column = "gender"),
            @Result(property = "user.address",column = "address")
    })

    UserTransaction getUserTransactionByID(int id);
    @InsertProvider(type = TransactionProvider.class, method = "createNewTransaction")
    int createNewTransaction(@Valid Transaction transaction);
    // @DeleteProvider(type = TransactionRepository.class,method = "deleteTransactionById")
     int deleteTransaction(@Param("id") int id);
    @SelectProvider(type = TransactionProvider.class,method = "getUserTransactionByID")
    @Results({
            @Result(column = "sender_account_id",property = "sender",one = @One(select = "getUserTransactionByID")),
            @Result(column = "receiver_account_id",property = "receiver",one = @One(select = "getUserTransactionByID")),
            @Result(column = "transfer_at",property = "transferAt")
    })
    List<Transaction>getTransactionByUserID(int userID);

}
