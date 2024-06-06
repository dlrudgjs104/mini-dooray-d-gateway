package com.nhnacademy.minidooraydgateway.domain;

import lombok.Data;

@Data
public class Project {
    private long id;
    private String name;
    private Status status;


    public enum Status {
        ACTIVE("활성"), ENDED("종료"), DORMANT("휴면");

        private final String description;

        Status(String description) {
            this.description = description;
        }

    }
}
