package com.nhnacademy.minidooraydgateway.dto;

import lombok.Builder;
import lombok.Value;

import java.util.List;

@Builder
public record UserIdsGetDto(List<String> emails) {
}
