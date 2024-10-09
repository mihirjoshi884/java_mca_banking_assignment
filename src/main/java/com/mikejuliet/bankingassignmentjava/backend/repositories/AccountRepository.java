package com.mikejuliet.bankingassignmentjava.backend.repositories;


import com.mikejuliet.bankingassignmentjava.backend.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,String> {
    Account save(Account account); // Create & Update

    List<Account> findAll(); // Read all

    Account findAccountByAccountId(String accId);
    Optional<Account> findById(String accountId); // Read one by ID

    void deleteById(String accountId); // Delete
}
