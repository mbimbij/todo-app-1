package com.example.todoapp.core;

public interface UserManager {
    User getLoggedInUser();

    void setLoggedInUser(User loggedInUser);
}
