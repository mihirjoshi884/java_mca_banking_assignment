package com.mikejuliet.bankingassignmentjava.backend.repositories;


import com.mikejuliet.bankingassignmentjava.backend.entities.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminRepository extends JpaRepository<Admin,String> {

    Admin save(Admin admin); // Create & Update

    List<Admin> findAll(); // Read all

    Optional<Admin> findById(String adminId); // Read one by ID

    void deleteById(String adminId); // Delete
}
