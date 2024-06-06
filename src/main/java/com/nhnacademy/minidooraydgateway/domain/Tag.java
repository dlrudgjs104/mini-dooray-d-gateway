package com.nhnacademy.minidooraydgateway.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class Tag {
    private Long tagId;

    @NotBlank
    private String tagName;

    private long taskId;

    private long projectId;
}
