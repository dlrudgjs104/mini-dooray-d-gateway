package com.nhnacademy.minidooraydgateway.dto;


import com.nhnacademy.minidooraydgateway.domain.Task;
import lombok.Builder;

@Builder
public record TaskReadResponseDto(Task task) {
}
