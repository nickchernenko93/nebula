package com.obrio.data.registration;

public enum Genders {

    MALE("Male"),
    FEMALE("Female"),
    NON_BINARY("Non–binary");

    private final String value;

    Genders(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
