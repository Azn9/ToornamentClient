package com.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum RegistrationType {

    TEAM("team"),
    PLAYER("player");

    private final String type;

    RegistrationType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
