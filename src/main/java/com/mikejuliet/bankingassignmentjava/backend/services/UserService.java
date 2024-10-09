package com.mikejuliet.bankingassignmentjava.backend.services;


import com.mikejuliet.bankingassignmentjava.backend.entities.Account;
import com.mikejuliet.bankingassignmentjava.backend.entities.Transactions;
import com.mikejuliet.bankingassignmentjava.backend.entities.User;
import com.mikejuliet.bankingassignmentjava.backend.entities.UserCredentials;
import com.mikejuliet.bankingassignmentjava.backend.enums.ResponseType;
import com.mikejuliet.bankingassignmentjava.backend.repositories.AccountRepository;
import com.mikejuliet.bankingassignmentjava.backend.repositories.TransactionRepository;
import com.mikejuliet.bankingassignmentjava.backend.repositories.UserCredentialsRepository;
import com.mikejuliet.bankingassignmentjava.backend.repositories.UserRepository;
import com.mikejuliet.bankingassignmentjava.backend.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserCredentialsRepository userCredentialsRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private AuthUtil authUtil;


    public ResponseType userLogin(String username, String password){
        UserCredentials userCredentials = userCredentialsRepository.findByUsername(username);
        String storedPassword = userCredentials.getPassword();
        if(authUtil.matches(password,storedPassword,userCredentials.getSalt())){
            return ResponseType.SUCCESS;
        }else return ResponseType.UNAUTHENTICATED;
    }

    // 1. Check Balance
    public double checkBalance(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Account account = user.getBankDetails().getAccount(); // Access user's account
        return account.getAccountBalance(); // Return the account balance
    }

    // 2. Deposit Money
    public String depositMoney(String userId, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Account account = user.getBankDetails().getAccount(); // Access user's account

        // Update balance
        account.setAccountBalance(account.getAccountBalance() + amount);
        accountRepository.save(account); // Save updated account

        // Log transaction
        Transactions transaction = new Transactions();
        transaction.setFromAccountId(account.getAccountId());
        transaction.setAmount(amount);
        transaction.setDate(new Date());
        transaction.setType("DEPOSIT");

        transactionRepository.save(transaction); // Save to Transaction table
        account.addTransaction(transaction); // Also add to Account's transaction list

        return "Deposit successful! New balance: " + account.getAccountBalance();
    }

    // 3. Change Password
    public String changePassword(String userId, String newPassword) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Encode the new password
        UserCredentials credentials = user.getCredentials();
        credentials.setPassword(
                authUtil.encodePassword(newPassword,credentials.getSalt())
        );
        userRepository.save(user); // Save user with new password

        return "Password changed successfully!";
    }

    // 4. View Transaction History
    public List<Transactions> viewTransactionHistory(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Account account = user.getBankDetails().getAccount(); // Access user's account

        // Get transactions from and to this account
        List<Transactions> transactionsFrom = transactionRepository.findByFromAccountId(account.getAccountId());
        List<Transactions> transactionsTo = transactionRepository.findByToAccountId(account.getAccountId());

        // Combine both lists
        transactionsFrom.addAll(transactionsTo);

        return transactionsFrom;
    }

    // 5. Make Transaction to Another Account
    public String makeTransaction(String userId, String toAccountId, double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Account fromAccount = user.getBankDetails().getAccount(); // Access user's account
        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("To account not found"));

        if (fromAccount.getAccountBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        }

        // Update balances
        fromAccount.setAccountBalance(fromAccount.getAccountBalance() - amount);
        toAccount.setAccountBalance(toAccount.getAccountBalance() + amount);

        accountRepository.save(fromAccount); // Save from account
        accountRepository.save(toAccount); // Save to account

        // Log transactions for both accounts
        Transactions transactionFrom = new Transactions();
        transactionFrom.setFromAccountId(fromAccount.getAccountId());
        transactionFrom.setToAccountId(toAccount.getAccountId());
        transactionFrom.setAmount(amount);
        transactionFrom.setDate(new Date());
        transactionFrom.setType("TRANSFER");

        Transactions transactionTo = new Transactions();
        transactionTo.setFromAccountId(toAccount.getAccountId());
        transactionTo.setToAccountId(fromAccount.getAccountId());
        transactionTo.setAmount(amount);
        transactionTo.setDate(new Date());
        transactionTo.setType("RECEIVE");

        transactionRepository.save(transactionFrom); // Save from transaction
        transactionRepository.save(transactionTo); // Save to transaction

        fromAccount.addTransaction(transactionFrom);
        toAccount.addTransaction(transactionTo);

        return "Transaction successful! New balance: " + fromAccount.getAccountBalance();
    }
}
