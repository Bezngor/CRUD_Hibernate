package com.bezngor.crud_hibernate.model;

public enum TeamStatus {
    ACTIVE(1),
    UNDER_REVIEW(2),
    DELETED(3);

    private Integer value;

    TeamStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
