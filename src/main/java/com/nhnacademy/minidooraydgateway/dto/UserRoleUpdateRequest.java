package com.nhnacademy.minidooraydgateway.dto;

import java.util.List;

public record UserRoleUpdateRequest(List<Long> emails, String role) {}
