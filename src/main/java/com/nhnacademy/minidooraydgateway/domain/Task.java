package com.nhnacademy.minidooraydgateway.domain;

import jakarta.validation.constraints.NotBlank;

public class Task {
    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        DONE;
    }

    private String taskName;

    private String taskDescription;

    @NotBlank
    private TaskStatus taskStatus;

    private Long taskId;

    private long projectId;

    private long milestoneId;

}
