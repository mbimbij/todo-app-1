package com.example.todoapp.cucumber;

import com.example.todoapp.core.Item;
import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.User;
import com.example.todoapp.core.UserRepository;
import com.example.todoapp.infra.UserManagerMock;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BackgroupStepDef {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserManagerMock userManager;

    @And("the todo items")
    public void theTodoItems(List<Item> items) {
        itemRepository.deleteAll();
        items.forEach(itemRepository::save);
    }

    @Given("users")
    public void loggedInUsers(List<String> userIds) {
        userIds.stream().map(User::createWithId).forEach(userRepository::save);
    }

    @Given("logged in user \"{user}\"")
    public void loggedInUser(User user) {
        userManager.setLoggedInUser(user);
    }
}
