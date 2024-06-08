package com.nhnacademy.minidooraydgateway.domain;

import lombok.Data;
import lombok.Getter;

@Data
public class User {

    private Long id;

    private String password;

    private String email;

    private Status status;

    private Role role;

    @Getter
    public enum Status {
        ACTIVE("가입"), ENDED("탈퇴"), DORMANT("휴면");

        private final String description;

        Status(String description) {
            this.description = description;
        }
    }

    public enum Role {
        MEMBER, PROJECT_MEMBER, PROJECT_ADMIN;
    }
}
