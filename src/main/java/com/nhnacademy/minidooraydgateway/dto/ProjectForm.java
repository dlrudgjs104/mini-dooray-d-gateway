package com.nhnacademy.minidooraydgateway.dto;

import com.nhnacademy.minidooraydgateway.domain.Project;

import java.util.List;

public record ProjectForm(String name, Project.Status status, String adminEmail, List<String> memberEmails) {
}
