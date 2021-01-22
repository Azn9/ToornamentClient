package com.toornament.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum StageType {

    GROUP("group"),
    LEAGUE("league"),
    SWISS("swiss"),
    SINGLE_ELIMINATION("single_elimination"),
    DOUBLE_ELIMINATION("double_elimination"),
    BRACKET_GROUP("bracket_group");

    private final String type;

    StageType(String type) {
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
