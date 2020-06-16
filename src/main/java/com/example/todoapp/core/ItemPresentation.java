package com.example.todoapp.core;

import java.util.UUID;

public class ItemPresentation {
    private final String id;
    private final String name;
    private final String state;

    private ItemPresentation(String id, String name, String state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public static ItemPresentation create(String name, String state) {
        return new ItemPresentation(UUID.randomUUID().toString(),
                name,
                state);
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }

    public static ItemPresentation createFromItem(Item item) {
        return new ItemPresentation(item.getId(), item.getName(), item.getState());
    }

    @Override
    public String toString() {
        return "ItemPresentation{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}
