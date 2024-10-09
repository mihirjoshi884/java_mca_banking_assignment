package com.mikejuliet.bankingassignmentjava.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class User {
    @Id
    private String userId;
    private String name;
    private long phoneNumber;
    private String email;
    @Column(unique = true)
    private String username;
    @OneToOne(cascade = CascadeType.ALL) // Add cascade
    private BankDetails bankDetails;

    @OneToOne(cascade = CascadeType.ALL) // Add cascade
    private UserCredentials credentials;
}
