package com.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TournamentStatus {

    SETUP("setup"),
    RUNNING("running"),
    COMPLETED("completed"),
    PENDING("pending");

    private final String status;

    TournamentStatus(String status) {
        this.status = status;
    }

    @JsonValue
    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return status;
    }
}
