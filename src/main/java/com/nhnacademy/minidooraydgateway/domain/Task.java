package com.nhnacademy.minidooraydgateway.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Task {
    public enum TaskStatus {
        TODO,
        IN_PROGRESS,
        DONE;
    }

    private String name;

    private String description;

    @NotBlank
    private TaskStatus status;

    private Long taskId;

    private long projectId;

    private long milestoneId;

}
