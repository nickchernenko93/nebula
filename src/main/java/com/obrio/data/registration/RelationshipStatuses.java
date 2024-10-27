package com.obrio.data.registration;

public enum RelationshipStatuses {

    SINGLE("Single"),
    ENGAGED("Engaged");

    private final String value;

    RelationshipStatuses(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
