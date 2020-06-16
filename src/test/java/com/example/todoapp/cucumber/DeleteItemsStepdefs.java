package com.example.todoapp.cucumber;

import com.example.todoapp.core.DeleteItemsUsecase;
import com.example.todoapp.core.ListItemsResponseModel;
import com.example.todoapp.core.User;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        @LocalServerPort
        private int port;
        @Autowired
        private TestRestTemplate restTemplate;

        @When("\"{user}\" deletes the following items by id through rest controller")
        public void deletesTheFollowingItemsThroughRestController(User user, List<String> ids) {
            String url = String.format("http://localhost:%d/deleteItems", port);
            ResponseEntity<Void> entity = restTemplate.postForEntity(url, ids, Void.class);
            Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        }
        @When("\"{user}\" deletes the item {string} with a rest delete")
        public void deletesTheItemWithARestDelete(User user, String itemId) {
            String url = String.format("http://localhost:%d/item/%s", port, itemId);
            restTemplate.delete(url);
        }
    }
}
