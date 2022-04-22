package com.aj2814.homeAutomation.services.deviceRegistry.view;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;


public class Room {
    private final int id;
    private final String name;

    @JsonCreator
    public Room(
            @JsonProperty("id") int id,
            @JsonProperty("name") String name
    ) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name;
    }
}
