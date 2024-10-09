package com.mikejuliet.bankingassignmentjava.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Entity
public class Admin {
    @Id
    private String adminId;
    private String name;
    private long phoneNumber;
    private String email;
    private String username;
    @OneToOne
    private UserCredentials credentials;
}
