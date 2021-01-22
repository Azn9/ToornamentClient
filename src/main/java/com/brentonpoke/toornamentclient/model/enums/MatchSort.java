package com.brentonpoke.toornamentclient.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MatchSort {

    STRUCTURE("structure"),
    SCHEDULE("schedule"),
    LATEST("latest_results");

    private final String name;

    MatchSort(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
