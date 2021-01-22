package com.brentonpoke.toornamentclient.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Attribute {
    EDIT("edit"),
    REPORT("report"),
    DELETE("delete"),
    AUTHORIZE("authorize"),
    FILL("fill"),
    REGISTER("register");

    private final String name;

    Attribute(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
