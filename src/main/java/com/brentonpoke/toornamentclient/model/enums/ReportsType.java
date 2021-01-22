package com.brentonpoke.toornamentclient.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ReportsType {

    DISPUTE("dispute"),
    REPORT("report");

    private final String name;

    ReportsType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
