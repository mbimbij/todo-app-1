package com.example.todoapp.cucumber;

import com.example.todoapp.core.ItemRepository;
import com.example.todoapp.core.ListItemsResponseModel;
import com.example.todoapp.core.User;
import com.example.todoapp.core.UserManager;
import com.example.todoapp.infra.UserManagerMock;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ListItemsRestStepDef {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserManagerMock userManager;

    private ListItemsResponseModel response;

    @Given("logged in user \"{user}\"")
    public void loggedInUser(User user) {
        userManager.setLoggedInUser(user);
    }

    @When("calling listItems rest method")
    public void callingListItemsRestMethod(DataTable dataTable) {
        String url = String.format("http://localhost:%d/listItems", port);
        ResponseEntity<ListItemsResponseModel> entity = restTemplate.getForEntity(url, ListItemsResponseModel.class);
        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println();
    }

    @Then("presented items are")
    public void presentedItemsAre(DataTable dataTable) {
        System.out.println();
    }
}
