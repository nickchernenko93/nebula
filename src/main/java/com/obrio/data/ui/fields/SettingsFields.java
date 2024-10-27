package com.obrio.data.ui.fields;

public enum SettingsFields {

    NAME("Name"),
    DATE_OF_BIRTH("Date of Birth"),
    PLACE_OF_BIRTH("Place of Birth"),
    GENDER("Gender"),
    RELATIONSHIP_STATUS("Relationship status"),
    EMAIL("Email"),
    LOGIN_METHOD("Login Method"),
    USER_ID("User ID");

    private final String value;

    SettingsFields(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
