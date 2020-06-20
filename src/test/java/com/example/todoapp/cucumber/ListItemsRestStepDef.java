package com.example.todoapp.cucumber;

import com.example.todoapp.core.ListItemsResponseModel;
import com.example.todoapp.core.User;
import com.example.todoapp.core.UserManager;
import com.example.todoapp.infra.UserManagerMock;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

public class ListItemsRestStepDef {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    private ListItemsResponseModel response;
    @Autowired
    UserManager userManager;

    @When("user \"{user}\" calls listItems rest method")
    public void callingListItemsRestMethod(User user) {
        String url = String.format("http://localhost:%d/listItems", port);
        ResponseEntity<ListItemsResponseModel> entity = restTemplate
                .withBasicAuth(user.getName(),"pass")
                .getForEntity(url, ListItemsResponseModel.class);
        assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
        response = entity.getBody();
    }

    @Then("presented items though rest interface are")
    public void presentedItemsAre(DataTable expectedItemList) {
        DataTable actualItemList = TypeConverters.toDataTable(response);
        expectedItemList.unorderedDiff(actualItemList);
        assertThat(response.items)
                .as("id of presented items should not be blank")
                .allMatch(itemPresentation -> StringUtils.isNotBlank(itemPresentation.getId()));
    }
}
