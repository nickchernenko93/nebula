package com.obrio.data.registration;

public enum Interests {

    LOVE("love"),
    MONEY("money");

    private final String value;

    Interests(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
    }
