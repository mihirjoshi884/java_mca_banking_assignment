package com.mikejuliet.bankingassignmentjava.backend.entities;


import com.mikejuliet.bankingassignmentjava.backend.enums.BankName;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Component
@Getter @Setter
public class Banks {

    private static final List<HashMap<BankName, String>> BANK_NAME_LIST;

    static {
        BANK_NAME_LIST = new ArrayList<>();

        HashMap<BankName, String> icici = new HashMap<>();
        icici.put(BankName.ICICI, "ICIC0000001");
        BANK_NAME_LIST.add(icici);

        HashMap<BankName, String> kotak = new HashMap<>();
        kotak.put(BankName.KOTAK, "KKBK0000001");
        BANK_NAME_LIST.add(kotak);

        HashMap<BankName, String> centralBank = new HashMap<>();
        centralBank.put(BankName.CENTRAL_BANK, "CBIN0280001");
        BANK_NAME_LIST.add(centralBank);

        HashMap<BankName, String> bob = new HashMap<>();
        bob.put(BankName.BOB, "BARB0DBSAUR");
        BANK_NAME_LIST.add(bob);
    }

    public static List<HashMap<BankName, String>> getBankNameList() {
        return BANK_NAME_LIST;
    }

    public static Optional<String> getIfscCode(String bankName) {
        try {
            BankName bankNameEnum = BankName.valueOf(bankName.toUpperCase());
            return BANK_NAME_LIST.stream()
                    .filter(bank -> bank.containsKey(bankNameEnum))
                    .map(bank -> bank.get(bankNameEnum))
                    .findFirst();
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
