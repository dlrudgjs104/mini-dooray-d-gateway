package com.nhnacademy.minidooraydgateway.util;

import static org.junit.jupiter.api.Assertions.*;

import com.nhnacademy.minidooraydgateway.auth.CustomUserDetails;
import com.nhnacademy.minidooraydgateway.domain.User;
import com.nhnacademy.minidooraydgateway.service.RedisService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SecurityContextUtilTest {

    @Mock
    private RedisService redisService;

    @InjectMocks
    private SecurityContextUtil securityContextUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        SecurityContextHolder.clearContext();
    }

    @Test
    @DisplayName("SecurityContext에서 사용자 세부 정보 가져오기")
    void getCurrentUserDetails_fromSecurityContext() {
        // Given
        User user = new User();
        user.setId("1");
        user.setEmail("email@example.com");
        user.setPassword("password");
        user.setStatus(User.Status.ACTIVE);
        user.setRole(User.Role.PROJECT_ADMIN);

        CustomUserDetails userDetails = new CustomUserDetails(user, Collections.singletonList(new SimpleGrantedAuthority("PROJECT_ADMIN")));
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // When
        Optional<CustomUserDetails> result = securityContextUtil.getCurrentUserDetails();

        // Then
        assertTrue(result.isPresent());
        assertEquals(userDetails, result.get());
        assertEquals("email@example.com", result.get().getUsername());
    }

    @Test
    @DisplayName("Redis에서 사용자 세부 정보 가져오기")
    void getCurrentUserDetails_fromRedis() {
        // Given
        User user = new User("1", "email@example.com", "password", User.Status.ACTIVE);
        CustomUserDetails userDetails = new CustomUserDetails(user, Collections.emptyList());
        when(redisService.getUserDetailsFromSession(anyString())).thenReturn(Optional.of(userDetails));

        // When
        Optional<CustomUserDetails> result = securityContextUtil.getCurrentUserDetails();

        // Then
        assertTrue(result.isPresent());
        assertEquals(userDetails, result.get());
        assertEquals("email@example.com", result.get().getUsername());
    }

    @Test
    @DisplayName("현재 사용자 ID 가져오기")
    void getCurrentUserId() {
        // Given
        User user = new User("1", "email@example.com", "password", User.Status.ACTIVE);
        CustomUserDetails userDetails = new CustomUserDetails(user, Collections.emptyList());
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // When
        Optional<Long> result = securityContextUtil.getCurrentUserId();

        // Then
        assertTrue(result.isPresent());
        assertEquals(1L, result.get());
    }

    @Test
    @DisplayName("사용자가 특정 권한을 가지고 있는지 확인하기")
    void hasAuthority() {
        // Given
        User user = new User("1", "email@example.com", "password", User.Status.ACTIVE);
        CustomUserDetails userDetails = new CustomUserDetails(user, Collections.singletonList(new SimpleGrantedAuthority("PROJECT_ADMIN")));
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // When
        boolean result = securityContextUtil.hasAuthority("PROJECT_ADMIN");

        // Then
        assertTrue(result);
    }

    @Test
    @DisplayName("사용자가 특정 권한을 가지고 있지 않은지 확인하기")
    void hasAuthority_notPresent() {
        // Given
        User user = new User("1", "email@example.com", "password", User.Status.ACTIVE);
        CustomUserDetails userDetails = new CustomUserDetails(user, Collections.singletonList(new SimpleGrantedAuthority("PROJECT_MEMBER")));
        Authentication authentication = mock(Authentication.class);
        when(authentication.getPrincipal()).thenReturn(userDetails);

        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // When
        boolean result = securityContextUtil.hasAuthority("PROJECT_ADMIN");

        // Then
        assertFalse(result);
    }
}
