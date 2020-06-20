package com.example.todoapp.core;

public class CreateItemInput {
    private final String userName;
    private final String name;
    private final String state;

    public CreateItemInput(String userName, String name, String state) {

        this.userName = userName;
        this.name = name;
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public String getName() {
        return name;
    }

    public String getState() {
        return state;
    }
}
