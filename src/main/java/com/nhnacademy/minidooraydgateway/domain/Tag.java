package com.nhnacademy.minidooraydgateway.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Tag {
    private Long id;

    @NotBlank
    private String name;

    private long taskId;

    private long projectId;
}
