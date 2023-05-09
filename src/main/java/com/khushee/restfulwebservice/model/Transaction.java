package com.khushee.restfulwebservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Transaction {
    private int id;
    private UserTransaction sender;
    private UserTransaction receiver;
    private double amount;
    private String remark;
    private Timestamp transferAt;
}
