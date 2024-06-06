package com.nhnacademy.minidooraydgateway.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class Milestone {

    private Long milestoneId;

    @NotBlank
    private String milestoneName;

    private Integer milestoneProgress;

    private ZonedDateTime milestoneStartDate;

    private ZonedDateTime milestoneEndDate;

    private long projectId;

}
