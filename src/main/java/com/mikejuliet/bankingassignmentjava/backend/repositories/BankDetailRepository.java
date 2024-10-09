package com.mikejuliet.bankingassignmentjava.backend.repositories;


import com.mikejuliet.bankingassignmentjava.backend.entities.BankDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BankDetailRepository extends JpaRepository<BankDetails,String> {

    BankDetails save(BankDetails bankDetails); // Create & Update

    List<BankDetails> findAll(); // Read all

    Optional<BankDetails> findById(String bankId); // Read one by ID

    void deleteById(String bankId); // Delete
}
