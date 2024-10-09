package com.mikejuliet.bankingassignmentjava.backend.entities;

import jakarta.persistence.CascadeType;
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
public class BankDetails {

    @Id
    private String bankId;
    private String bankName;
    private String IFCScode;
    @OneToOne(cascade = CascadeType.ALL)
    private Account account;
}
