package com.obrio.data.registration;

public enum Goals {
    UNDERSTAND_MYSELF("Understand myself and others better"),
    FIND_PERFECT_MATCH("Find my perfect match"),
    RECEIVE_DAILY_INSIGHT_AND_TIPS("Receive daily insights and tips"),
    IMPROVE_RELATIONSHIP("Improve relationship with my partner");

    private final String value;

    Goals(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
