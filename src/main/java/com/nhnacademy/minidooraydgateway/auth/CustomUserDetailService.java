package com.nhnacademy.minidooraydgateway.auth;

import com.nhnacademy.minidooraydgateway.domain.User;
import com.nhnacademy.minidooraydgateway.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    public static final String DEFAULT_ROLE = "ROLE_USER";

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserById(username);
        if (user == null) {
            throw new UsernameNotFoundException("해당 아이디는 찾을 수 없습니다.");
        }

        return new org.springframework.security.core.userdetails.User(user.getId(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(DEFAULT_ROLE)));
    }
}
