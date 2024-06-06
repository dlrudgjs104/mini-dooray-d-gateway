package com.nhnacademy.minidooraydgateway.client;

import com.nhnacademy.minidooraydgateway.domain.User;
import com.nhnacademy.minidooraydgateway.dto.UserDto;
import com.nhnacademy.minidooraydgateway.dto.UserRoleUpdateRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "accountService", url = "${api.account-service.base-url}")
public interface AccountServiceClient {

//    @GetMapping("/users")
//    ResponseEntity<Page<User>> getAllUsers(Pageable pageable);

    @PostMapping("/users")
    ResponseEntity<Void> saveUser(@RequestBody UserDto user);

    @GetMapping("/users/{userId}")
    ResponseEntity<User> getUserById(@PathVariable("userId") String userId);

    @PutMapping("/users/roles")
    ResponseEntity<Void> updateUserRole(@RequestBody UserRoleUpdateRequest request);

    @GetMapping("/users/ids")
    ResponseEntity<List<Long>> getUserIdsByEmails(@RequestParam("emails") List<String> emails);
}