package com.example.todoapp.cucumber;

import com.example.todoapp.core.DeleteItemsUsecase;
import com.example.todoapp.core.User;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DeleteItemsStepdefs {

    public static class UseCaseTest{
        @Autowired
        private DeleteItemsUsecase usecase;

        @When("\"{user}\" delete the items by id")
        public void deleteTheItems(User user, List<String> ids) {
            usecase.deleteByIds(ids);
        }
    }

    public static class RestInterfaceTest{
        @When("\"{user}\" deletes the following items by id through rest controller")
        public void deletesTheFollowingItemsThroughRestController(User user, List<String> ids) {
        }
    }
}
