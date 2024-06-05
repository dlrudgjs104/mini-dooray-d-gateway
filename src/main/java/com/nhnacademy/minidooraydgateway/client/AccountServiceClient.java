package com.nhnacademy.minidooraydgateway.client;

import com.nhnacademy.minidooraydgateway.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "accountService", url = "${api.account-service.base-url}")
public interface AccountServiceClient {

    @GetMapping("/users")
    ResponseEntity<Page<User>> getAllUsers(Pageable pageable);

    @PostMapping("/users")
    ResponseEntity<Void> saveUser(@RequestBody User user);

    @GetMapping("/users/{userId}")
    ResponseEntity<User> getUserById(@PathVariable("userId") String userId);
}