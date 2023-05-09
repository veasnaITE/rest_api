package com.khushee.restfulwebservice.repository.provider;

import com.khushee.restfulwebservice.model.Transaction;
import org.apache.ibatis.jdbc.SQL;

public class TransactionProvider {
    public static String getTransactions(){
        return new SQL(){
            {
                SELECT("*");
                FROM("transaction_tb");

            }
        }.toString();
    }
    public static String getUserTransactionByID(int id){
        return new SQL(){
            {
               SELECT("*");
                FROM("transaction_tb");
                WHERE("id=#{id}");
            }
        }.toString();
    }

    public static String deleteTransactionById(int id){
        return new SQL(){{
            DELETE_FROM("transaction_tb");
            WHERE("id = #{id}");
        }}.toString();
    }

    public static String createNewTransaction(Transaction trequest){
        return new SQL(){{
            INSERT_INTO("transaction_tb");
            VALUES("sender_account_id","#{sender_account_id}");
            VALUES("receiver_account_id","#{receiver_account_id}");
            VALUES("amount","#{amount}");
            VALUES("remark","#{remark}");
            VALUES("transfer_at","#{transfer_at}");
        }}.toString();
    }
}
