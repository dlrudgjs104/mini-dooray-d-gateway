package com.nhnacademy.minidooraydgateway.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDto {

    @NotBlank
    private String id;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;
}
