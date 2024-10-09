package com.mikejuliet.bankingassignmentjava.backend.services;


import com.mikejuliet.bankingassignmentjava.backend.entities.Admin;
import com.mikejuliet.bankingassignmentjava.backend.entities.User;
import com.mikejuliet.bankingassignmentjava.backend.entities.UserCredentials;
import com.mikejuliet.bankingassignmentjava.backend.enums.ResponseType;
import com.mikejuliet.bankingassignmentjava.backend.enums.UserType;
import com.mikejuliet.bankingassignmentjava.backend.repositories.AdminRepository;
import com.mikejuliet.bankingassignmentjava.backend.repositories.UserCredentialsRepository;
import com.mikejuliet.bankingassignmentjava.backend.repositories.UserRepository;
import com.mikejuliet.bankingassignmentjava.backend.utils.AuthUtil;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

    private static final Logger logger = LoggerFactory.getLogger(AdminService.class);


    @Autowired
    private AuthUtil authUtil;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCredentialsRepository userCredentialsRepository;



    public ResponseType adminLogin(String username, String password){
        UserCredentials adminCredentials = userCredentialsRepository.findByUsername(username);
        String storedPassword = adminCredentials.getPassword();
        if(authUtil.matches(password,storedPassword,adminCredentials.getSalt())){
            return ResponseType.SUCCESS;
        }else return ResponseType.UNAUTHENTICATED;
    }
    @Transactional
    public User saveUsers(User user, String password) {
        logger.info("Saving new user: {}", user.getUsername());
        return saveEntity(user,password , UserType.USER);
    }

    @Transactional
    public Admin saveAdmin(Admin admin, String password) {
        logger.info("Saving new admin: {}", admin.getUsername());
        return saveEntity(admin, password,UserType.ADMIN);
    }

    private <T> T saveEntity(T entity,String password ,UserType userType) {
        if (entity instanceof User) {
            User user = (User) entity;
            checkUsernameAvailability(user.getUsername());

            String userId = userType.toString().concat(UUID.randomUUID().toString());
            String userCredId = "Cred" + UUID.randomUUID().toString();

            UserCredentials credentials = createUserCredentials(user.getUsername(),password,userCredId, userType);
            userCredentialsRepository.save(credentials);

            user.setUserId(userId);
            user.setCredentials(credentials);

            userRepository.save(user);


            return (T) user;
        } else if (entity instanceof Admin) {
            Admin admin = (Admin) entity;
            checkUsernameAvailability(admin.getUsername());

            String adminId = userType.toString().concat(UUID.randomUUID().toString());
            String adminCredId = "Cred" + UUID.randomUUID().toString();

            UserCredentials credentials = createUserCredentials(admin.getUsername(),password,adminCredId, userType);
            userCredentialsRepository.save(credentials);

            admin.setAdminId(adminId);
            admin.setCredentials(credentials);

            adminRepository.save(admin);

            return (T) admin;
        } else {
            throw new IllegalArgumentException("Unsupported entity type");
        }
    }

    private void checkUsernameAvailability(String username) {
        UserCredentials existingCredentials = userCredentialsRepository.findByUsername(username);
        if (existingCredentials != null) {
            throw new UsernameAlreadyExistsException("Username already exists: " + username);
        }
    }

    private UserCredentials createUserCredentials(String username,String password, String userCredId, UserType userType) {
        UserCredentials credentials = new UserCredentials();
        credentials.setSubject(userCredId);
        credentials.setUsername(username);
        credentials.setUserType(userType);
        String salt = authUtil.generateSalt();
        credentials.setPassword(authUtil.encodePassword(password,salt));

        return credentials;
    }


    private static class UsernameAlreadyExistsException extends RuntimeException {
        public UsernameAlreadyExistsException(String message) {
            super(message);
        }
    }
}
