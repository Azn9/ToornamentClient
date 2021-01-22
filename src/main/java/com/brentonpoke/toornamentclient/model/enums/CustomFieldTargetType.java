package com.brentonpoke.toornamentclient.model.enums;

public enum CustomFieldTargetType {

    TEAM("team"),
    PLAYER("player"),
    TEAM_PLAYER("team_player");

    private final String name;

    CustomFieldTargetType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
