package com.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Result {

    UNKNOWN("unknown"),
    WIN("win"),
    DRAW("draw"),
    LOSS("loss");

    private final String name;

    Result(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

}
