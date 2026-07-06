package com.jungbauer.securitytest.model.enums;

public enum RoleEnum {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    OWNER("ROLE_OWNER");

    private final String text;

    RoleEnum(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
