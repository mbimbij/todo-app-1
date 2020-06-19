package com.example.todoapp.infra;

import com.example.todoapp.core.User;
import com.example.todoapp.core.UserManager;

public class UserManagerMock implements UserManager {
    private User loggedInUser;

    public UserManagerMock(String loggedInUserId) {
        this.loggedInUser = User.createWithId(loggedInUserId);
    }

    @Override
    public User getLoggedInUser() {
        return loggedInUser;
    }

    @Override
    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }
}
