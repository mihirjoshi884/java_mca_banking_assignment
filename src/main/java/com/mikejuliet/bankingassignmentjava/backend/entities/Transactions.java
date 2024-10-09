package com.mikejuliet.bankingassignmentjava.backend.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId; // Unique identifier for each transaction

    private String fromAccountId; // ID of the sender's account
    private String toAccountId; // ID of the receiver's account (null if it's a deposit)

    private double amount; // Amount involved in the transaction
    private Date date; // Date of the transaction

    private String type; // Type of transaction (DEPOSIT, WITHDRAWAL, TRANSFER, RECEIVE)
}

