package com.mikejuliet.bankingassignmentjava.backend.repositories;


import com.mikejuliet.bankingassignmentjava.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,String > {

    User save(User user); // Create & Update

    List<User> findAll(); // Read all

    Optional<User> findById(String userId); // Read one by ID

    User getByUsername(String username); // Read by username

    void deleteById(String userId); // Delete
}
