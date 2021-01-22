package com.brentonpoke.toornamentclient.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MatchType {

    DUEL("duel"),
    FFA("ffa"),
    BYE("bye");

    private final String name;

    MatchType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
