package com.mikejuliet.bankingassignmentjava.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

    @Id
    private String accountId;

    @Column(unique = true)
    private UUID accountNumber;

    private String accountType;
    private double accountBalance;

    @OneToMany(mappedBy = "fromAccountId", cascade = CascadeType.ALL) // Transactions from this account
    private List<Transactions> transactionsFrom = new ArrayList<>();

    @OneToMany(mappedBy = "toAccountId", cascade = CascadeType.ALL) // Transactions to this account
    private List<Transactions> transactionsTo = new ArrayList<>();

    // Method to add a transaction from this account
    public void addTransaction(Transactions transaction) {
        if ("DEPOSIT".equals(transaction.getType()) || "TRANSFER".equals(transaction.getType())) {
            transactionsFrom.add(transaction);
        } else if ("RECEIVE".equals(transaction.getType())) {
            transactionsTo.add(transaction);
        }
    }
}
