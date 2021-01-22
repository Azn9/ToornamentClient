package com.brentonpoke.toornamentclient.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Sort {

    ASCENDING("created_asc"),
    DESCENDING("created_desc"),
    ALPHABETIC("alphabetic");

    private final String sort;

    Sort(String sort) {
        this.sort = sort;
    }

    @JsonValue
    public String getScope() {
        return sort;
    }

    @Override
    public String toString() {
        return sort;
    }
}
