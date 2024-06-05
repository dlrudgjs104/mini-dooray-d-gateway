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
    private BindingResult bindingResult;

    @Mock
    private Model model;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("회원가입 폼 반환 테스트")
    public void testSignupForm() {
        String viewName = accountController.signupForm(model);
        assertEquals("account/signup", viewName);
        verify(model).addAttribute(eq("user"), any(UserDto.class));
    }

    @Test
    @DisplayName("회원가입 제출 - 유효성 검사 실패 시 테스트")
    public void testSignupSubmit_WithValidationErrors() {
        when(bindingResult.hasErrors()).thenReturn(true);
        when(bindingResult.getFieldErrors()).thenReturn(Collections.singletonList(new FieldError("userDto", "id", "default message")));

        String viewName = accountController.signupSubmit(new UserDto(), bindingResult, model);
        assertEquals("account/signup", viewName);
        verify(model).addAttribute(eq("error"), eq("id이(가) 잘못되었습니다."));
    }

    @Test
    @DisplayName("회원가입 제출 - 서비스 예외 발생 시 테스트")
    public void testSignupSubmit_WithServiceException() {
        UserDto userDto = new UserDto();
        when(bindingResult.hasErrors()).thenReturn(false);
        doThrow(new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 사용자 데이터입니다.")).when(userService).saveUser(any(UserDto.class));

        String viewName = accountController.signupSubmit(userDto, bindingResult, model);
        assertEquals("account/signup", viewName);
        verify(model).addAttribute(eq("error"), eq("잘못된 사용자 데이터입니다."));
    }

    @Test
    @DisplayName("회원가입 제출 - 성공 시 테스트")
    public void testSignupSubmit_Success() {
        UserDto userDto = new UserDto();
        when(bindingResult.hasErrors()).thenReturn(false);

        String viewName = accountController.signupSubmit(userDto, bindingResult, model);
        assertEquals("redirect:/login", viewName);
        verify(userService).saveUser(any(UserDto.class));
    }
}
