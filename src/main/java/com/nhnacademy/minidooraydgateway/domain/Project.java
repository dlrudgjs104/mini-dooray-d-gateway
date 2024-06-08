package com.nhnacademy.minidooraydgateway.domain;

import lombok.Data;

@Data
public class Project {
    private long id;
    private String name;
    private Status status;


    public enum Status {
        TODO("할일"), IN_PROGRESS("진행중"), DONE("완료");

        private final String description;

        Status(String description) {
            this.description = description;
        }

    }
}
