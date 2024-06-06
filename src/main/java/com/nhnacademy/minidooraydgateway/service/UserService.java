package com.nhnacademy.minidooraydgateway.service;

import com.nhnacademy.minidooraydgateway.client.AccountServiceClient;
import com.nhnacademy.minidooraydgateway.domain.User;
import com.nhnacademy.minidooraydgateway.dto.UserDto;
import com.nhnacademy.minidooraydgateway.dto.UserRoleUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final AccountServiceClient accountServiceClient;
    private final PasswordEncoder passwordEncoder;

    public void saveUser(UserDto user) {
        UserDto encoded = new UserDto(
                user.email(),
                passwordEncoder.encode(user.password())
        );

        ResponseEntity<Void> response = accountServiceClient.saveUser(encoded);
        if (response.getStatusCode() != HttpStatus.CREATED) {
            if (response.getStatusCode() == HttpStatus.BAD_REQUEST) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 사용자 데이터입니다.");
            } else if (response.getStatusCode() == HttpStatus.CONFLICT) {
                throw new ResponseStatusException(HttpStatus.CONFLICT, "사용자가 이미 존재합니다.");
            } else {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "예상치 못한 오류가 발생했습니다.");
            }
        }

    }

    public User getUserById(String userId) {
        ResponseEntity<User> response = accountServiceClient.getUserById(userId);
        return response.getBody();
    }

    public User getUserByEmail(String email) {
        ResponseEntity<User> response = accountServiceClient.getUserById(email);
        return response.getBody();
    }

    public void updateUserRole(List<Long> userIds, String role) {
        UserRoleUpdateRequest request = new UserRoleUpdateRequest(userIds, role);
        accountServiceClient.updateUserRole(request);
    }

    public List<Long> getUserIdsByEmails(List<String> emails) {
        ResponseEntity<List<Long>> response = accountServiceClient.getUserIdsByEmails(emails);
        return response.getBody();
    }
}
