package com.nhnacademy.minidooraydgateway.controller;

import com.nhnacademy.minidooraydgateway.dto.UserDto;
import com.nhnacademy.minidooraydgateway.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AccountControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private Model model;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("GET /signup - 폼 반환")
    public void testSignupForm() {
        String viewName = accountController.signupForm(model);

        verify(model).addAttribute(eq("userDto"), any(UserDto.class));
        assertEquals("account/signup", viewName);
    }

    @Test
    @DisplayName("POST /signup - 유효성 검증 실패")
    public void testSignupSubmitValidationError() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(new FieldError("userDto", "username", "Username is required")));

        String viewName = accountController.signupSubmit(new UserDto("", ""), bindingResult, model);

        verify(model).addAttribute(eq("error"), eq("username이(가) 잘못되었습니다."));
        assertEquals("account/signup", viewName);
    }

    @Test
    @DisplayName("POST /signup - 사용자 저장 성공")
    public void testSignupSubmitSuccess() {
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = accountController.signupSubmit(new UserDto("username", "password"), bindingResult, model);

        verify(userService).saveUser(any(UserDto.class));
        assertEquals("redirect:/login", viewName);
    }

    @Test
    @DisplayName("POST /signup - 사용자 저장 중 예외 발생")
    public void testSignupSubmitException() {
        when(bindingResult.hasErrors()).thenReturn(false);
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists")).when(userService).saveUser(any(UserDto.class));

        String viewName = accountController.signupSubmit(new UserDto("username", "password"), bindingResult, model);

        verify(model).addAttribute(eq("error"), eq("User already exists"));
        assertEquals("account/signup", viewName);
    }
}
