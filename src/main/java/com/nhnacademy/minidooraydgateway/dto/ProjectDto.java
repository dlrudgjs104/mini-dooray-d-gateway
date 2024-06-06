package com.nhnacademy.minidooraydgateway.dto;

import com.nhnacademy.minidooraydgateway.domain.Project;

import java.util.List;

public record ProjectDto(String name, Project.Status status, Long adminUserId, List<Long> memberIds) {
}