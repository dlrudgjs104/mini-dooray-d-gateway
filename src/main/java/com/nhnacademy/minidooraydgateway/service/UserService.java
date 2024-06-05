package com.nhnacademy.minidooraydgateway.service;

import com.nhnacademy.minidooraydgateway.client.AccountServiceClient;
import com.nhnacademy.minidooraydgateway.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountServiceClient accountServiceClient;
    private final PasswordEncoder passwordEncoder;


    public Page<User> getAllUsers(Pageable pageable) {
        ResponseEntity<Page<User>> response = accountServiceClient.getAllUsers(pageable);
        return response.getBody();
    }

    public void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ResponseEntity<Void> response = accountServiceClient.saveUser(user);

    }

    public User getUserById(String userId) {
        ResponseEntity<User> response = accountServiceClient.getUserById(userId);
        return response.getBody();
    }

}
