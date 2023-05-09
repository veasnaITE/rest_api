package com.khushee.restfulwebservice.service;

import com.github.pagehelper.PageInfo;
import com.khushee.restfulwebservice.model.Transaction;

import java.util.List;

public interface TransactionService {
    public PageInfo<Transaction>getAllTransactions(int page, int size);
    int creatTransaction(Transaction transaction);
    int deleteTransaction(int id);
    List<Transaction> getTransactionByUserID(int userID);
}
