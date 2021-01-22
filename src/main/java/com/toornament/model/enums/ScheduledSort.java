package com.toornament.model.enums;

public enum ScheduledSort {

    SCHEDULED_ASC("scheduled_asc"),
    SCHEDULED_DESC(" scheduled_desc");

    private final String name;

    ScheduledSort(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
