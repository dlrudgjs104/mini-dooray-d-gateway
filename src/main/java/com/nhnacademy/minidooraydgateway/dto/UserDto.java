package com.nhnacademy.minidooraydgateway.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserDto(@Email @NotBlank String email,
                      @NotBlank String password) {
}
