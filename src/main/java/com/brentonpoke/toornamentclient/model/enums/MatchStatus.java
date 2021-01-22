package com.brentonpoke.toornamentclient.model.enums;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public enum MatchStatus {

    @JsonProperty
    PENDING("pending"),

    @JsonProperty
    RUNNING("running"),

    @JsonProperty
    COMPLETED("completed");

    private final String name;

    MatchStatus(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
