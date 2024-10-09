package com.mikejuliet.bankingassignmentjava.backend.entities;


import com.mikejuliet.bankingassignmentjava.backend.enums.UserType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity @Table(name="user_credentials", uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UserCredentials {

    @Id
    private String subject;
    private String salt;
    @Column(unique = true)
    private String username;
    private String password;
    private UserType userType;

}
