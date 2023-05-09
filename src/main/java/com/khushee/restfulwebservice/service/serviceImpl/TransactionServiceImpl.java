package com.khushee.restfulwebservice.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.khushee.restfulwebservice.model.Transaction;
import com.khushee.restfulwebservice.repository.TransactionRepository;
import com.khushee.restfulwebservice.service.TransactionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    TransactionServiceImpl(TransactionRepository transactionRepository){
        this.transactionRepository=transactionRepository;
    }
    @Override
    public PageInfo<Transaction> getAllTransactions(int page, int size) {
        PageHelper.startPage(page,size);
        return new PageInfo<>(transactionRepository.getAllTransactions()) ;
    }
    @Override
    public int creatTransaction(Transaction transaction) {
        return transactionRepository.createNewTransaction(transaction);
    }
    @Override
    public int deleteTransaction(int id) {
        return transactionRepository.deleteTransaction(id);
    }
    @Override
    public List<Transaction> getTransactionByUserID(int userID) {
        return transactionRepository.getTransactionByUserID(userID);
    }

}
