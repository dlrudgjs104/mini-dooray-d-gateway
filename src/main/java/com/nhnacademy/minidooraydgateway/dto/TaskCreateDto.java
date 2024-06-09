package com.nhnacademy.minidooraydgateway.dto;

import com.nhnacademy.minidooraydgateway.domain.Task;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record TaskCreateDto(String name, String description, @NotBlank Task.TaskStatus status, long milestoneId) {
}
