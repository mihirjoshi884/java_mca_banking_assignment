package com.mikejuliet.bankingassignmentjava.backend.repositories;


import com.mikejuliet.bankingassignmentjava.backend.entities.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transactions, Long> {

    // Find all transactions by fromAccountId
    List<Transactions> findByFromAccountId(String fromAccountId);

    // Find all transactions by toAccountId
    List<Transactions> findByToAccountId(String toAccountId);

    // Find all transactions by fromAccountId or toAccountId
    List<Transactions> findByFromAccountIdOrToAccountId(String fromAccountId, String toAccountId);
}
